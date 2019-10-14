package com.mayarafernandes.movieplayer.navigationBar.keepWatchingList

import com.mayarafernandes.movieplayer.MovieListPresenter
import com.mayarafernandes.movieplayer.navigationBar.keepWatchingList.repository.KeepWatchingRepository
import com.mayarafernandes.movieplayer.navigationBar.movies.repository.Movie
import com.mayarafernandes.movieplayer.navigationBar.movies.repository.MovieRepository
import com.mayarafernandes.movieplayer.navigationBar.movies.repository.service.MovieCallbacks
import com.mayarafernandes.movieplayer.navigationBar.movies.repository.storage.LocalMovieStorage
import com.mayarafernandes.movieplayer.navigationBar.movies.view.MovieListView
import com.mayarafernandes.movieplayer.navigationBar.movies.view.MovieViewModel
import java.util.concurrent.Executors

class KeepWatchingListController(
    private val movieRepository: MovieRepository,
    private val presenter: MovieListPresenter,
    private val view: MovieListView,
    private val keepWatchingRepository: KeepWatchingRepository,
    private val localMovieStorage: LocalMovieStorage
) {

    fun onViewCreated() {
        view.showProgressBar()

        val movies = localMovieStorage.returnMovieList()

        if (movies.isEmpty()) {
            movieRepository.returnMovieList(object : MovieCallbacks {
                override fun onSuccess(movieList: List<Movie>) {
                    setViewModel(movieList)
                }

                override fun onError() {
                    view.hideProgressBar()
                    view.hideMovieList()
                }
            })
        }

        else {
            setViewModel(movies)
        }
    }

    fun onSelectMovie(movie: MovieViewModel) {
        val selectedMovie = getSelectedMovie((movie))
    }

    fun saveProgress(movie: MovieViewModel, watched: Double) {
        Executors.newSingleThreadExecutor()
            .execute { keepWatchingRepository.saveProgress(movie, watched) }
    }

    private fun setViewModel(movieList: List<Movie>) {
        val viewModels = movieList.map { movie ->
            presenter.convertModel(movie)
        }

        val finalList = mutableListOf<MovieViewModel>()

        Executors.newSingleThreadExecutor().execute {
            val watchingList = keepWatchingRepository.returnKeepWatchingList()
            viewModels.map { movie ->
                val selected = watchingList.find { it.id == movie.id }
                if (selected != null) movie.progress = selected.progress
            }

            for(movie in viewModels) {
                if (movie.progress in 10..90) {
                    finalList.add(movie)
                }
            }

            view.setViewModel(finalList)
            view.hideProgressBar()
        }
    }

    /*private fun getWatchingList(viewModels: List<MovieViewModel>): List<MovieViewModel> {
        return viewModels.filter { it.progress in 10..90 }
    }*/

    private fun getSelectedMovie(movie: MovieViewModel): Movie {
        val movieId = movie.id
        return movieRepository.onMovieSelected(movieId)
    }

}
