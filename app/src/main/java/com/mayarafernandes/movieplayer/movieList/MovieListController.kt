package com.mayarafernandes.movieplayer.movieList

import android.util.Log
import android.widget.ToggleButton
import com.mayarafernandes.movieplayer.favorites.FavoritesRepository
import com.mayarafernandes.movieplayer.movieList.repository.Movie
import com.mayarafernandes.movieplayer.movieList.repository.MovieRepository
import com.mayarafernandes.movieplayer.movieList.repository.service.MovieCallbacks
import com.mayarafernandes.movieplayer.movieList.view.MovieListPresenter
import com.mayarafernandes.movieplayer.movieList.view.MovieListView
import com.mayarafernandes.movieplayer.movieList.view.MovieViewModel
import java.util.concurrent.Executors

class MovieListController(
    private val movieRepository: MovieRepository,
    private val presenter: MovieListPresenter,
    private val view: MovieListView,
    private val favoritesRepository: FavoritesRepository
) {

    fun onViewCreated() {
        view.showProgressBar(true)
        var viewModels: List<MovieViewModel>

        movieRepository.returnMovieList(object: MovieCallbacks {
            override fun onSuccess(movieList: List<Movie>) {
                viewModels = movieList.map { movie ->
                    presenter.convertModel(movie)
                }

                Executors.newSingleThreadExecutor().execute {
                    val favorites = favoritesRepository.returnFavorites()
                    viewModels.map { movie ->
                        if(favorites.find { it.id == movie.id } != null) movie.favorite = true
                    }
                }

                view.setViewModel(viewModels)
                view.showProgressBar(false)
            }

            override fun onError() {
                view.showProgressBar(false)
                view.showMovieList(false)
            }
        })
    }

    fun onSelectMovie(movie: MovieViewModel) {
        val movieId = movie.id
        val selectedMovie = movieRepository.onMovieSelected(movieId)
    }

    fun onFavorited(
        movie: MovieViewModel,
        favoriteButton: ToggleButton
    ) {
        val movieId = movie.id
        val movieSelected = movieRepository.onMovieSelected(movieId)

        if(favoriteButton.isChecked) {
            Executors.newSingleThreadExecutor().execute { favoritesRepository.saveFavorite(movieSelected) }
        }
        else {
            Executors.newSingleThreadExecutor().execute{ favoritesRepository.deleteFavorite(movieSelected) }
        }

        presenter.setFavorite(favoriteButton)
    }
}