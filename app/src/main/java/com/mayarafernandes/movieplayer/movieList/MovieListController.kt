package com.mayarafernandes.movieplayer.movieList

import android.util.Log
import com.mayarafernandes.movieplayer.movieList.service.MovieCallbacks
import com.mayarafernandes.movieplayer.movieList.view.MovieListPresenter
import com.mayarafernandes.movieplayer.movieList.view.MovieListView
import com.mayarafernandes.movieplayer.movieList.view.MovieViewModel

class MovieListController(
    private val movieRepository: MovieRepository,
    private val presenter: MovieListPresenter,
    private val view: MovieListView
) {

    fun onViewCreated() {
        movieRepository.returnMovieList(object: MovieCallbacks {
            override fun onSuccess(movieList: List<Movie>) {
                val viewModels = movieList.map { movie ->
                    presenter.convertModel(movie)
                }

                view.setViewModel(viewModels)
            }

            override fun onError() { Log.d("movieListController", "Deu erro") }
        })
    }

    fun onSelectMovie(movie: MovieViewModel) {
        val movieId = movie.id
        val selectedMovie = movieRepository.onMovieSelected(movieId)
    }
}