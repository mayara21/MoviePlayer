package com.mayarafernandes.movieplayer.movieList

import com.mayarafernandes.movieplayer.movieList.favorites.FavoritesRepository
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
        view.showProgressBar()
        var viewModels: List<MovieViewModel>

        movieRepository.returnMovieList(object: MovieCallbacks {
            override fun onSuccess(movieList: List<Movie>) {
                viewModels = movieList.map { movie ->
                    presenter.convertModel(movie)
                }

                Executors.newSingleThreadExecutor().execute {
                    val favorites = favoritesRepository.returnFavorites()

                    viewModels.map { movie ->
                        if(favorites.find { it.id == movie.id } != null) movie.isFavorite = true
                    }

                    view.setViewModel(viewModels)
                }

                view.hideProgressBar()
            }

            override fun onError() {
                view.hideProgressBar()
                view.hideMovieList()
            }
        })
    }

    fun onSelectMovie(movie: MovieViewModel) {
        val selectedMovie = getSelectedMovie((movie))
    }

    fun addToFavorites(movie: MovieViewModel) {
        movie.isFavorite = true
        val selectedMovie = getSelectedMovie(movie)
        Executors.newSingleThreadExecutor().execute { favoritesRepository.saveFavorite(selectedMovie) }
    }

    fun removeFromFavorites(movie: MovieViewModel) {
        movie.isFavorite = false
        val selectedMovie = getSelectedMovie(movie)
        Executors.newSingleThreadExecutor().execute{ favoritesRepository.deleteFavorite(selectedMovie) }
    }

    fun getFavorites(viewModels: List<MovieViewModel>): List<MovieViewModel> {
        return viewModels.filter { it.isFavorite }
    }

    private fun getSelectedMovie(movie: MovieViewModel): Movie {
        val movieId = movie.id
        return movieRepository.onMovieSelected(movieId)
    }
}