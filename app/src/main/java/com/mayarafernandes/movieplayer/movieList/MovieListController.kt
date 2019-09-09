package com.mayarafernandes.movieplayer.movieList

import android.widget.ToggleButton
import com.mayarafernandes.movieplayer.movieList.repository.Movie
import com.mayarafernandes.movieplayer.movieList.repository.MovieRepository
import com.mayarafernandes.movieplayer.movieList.repository.service.MovieCallbacks
import com.mayarafernandes.movieplayer.movieList.view.MovieListPresenter
import com.mayarafernandes.movieplayer.movieList.view.MovieListView
import com.mayarafernandes.movieplayer.movieList.view.MovieViewModel

class MovieListController(
    private val movieRepository: MovieRepository,
    private val presenter: MovieListPresenter,
    private val view: MovieListView
) {

    fun onViewCreated() {
        view.showProgressBar(true)
        movieRepository.returnMovieList(object: MovieCallbacks {
            override fun onSuccess(movieList: List<Movie>) {
                val viewModels = movieList.map { movie ->
                    presenter.convertModel(movie)
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
        movie.favorite = true
        //salvar no BD Room
        presenter.setFavorite(favoriteButton)
    }
}