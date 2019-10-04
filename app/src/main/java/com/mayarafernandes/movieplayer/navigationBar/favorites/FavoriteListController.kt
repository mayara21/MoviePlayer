package com.mayarafernandes.movieplayer.navigationBar.favorites

import com.mayarafernandes.movieplayer.navigationBar.keepWatchingList.repository.KeepWatchingRepository
import com.mayarafernandes.movieplayer.navigationBar.movies.repository.Movie
import com.mayarafernandes.movieplayer.navigationBar.movies.repository.MovieRepository
import com.mayarafernandes.movieplayer.navigationBar.movies.repository.service.MovieCallbacks
import com.mayarafernandes.movieplayer.navigationBar.movies.repository.storage.LocalMovieStorage
import com.mayarafernandes.movieplayer.navigationBar.movies.view.MovieListPresenter
import com.mayarafernandes.movieplayer.navigationBar.movies.view.MovieListView
import com.mayarafernandes.movieplayer.navigationBar.movies.view.MovieViewModel
import java.util.concurrent.Executors

class FavoriteListController(
    private val movieRepository: MovieRepository,
    private val presenter: MovieListPresenter,
    private val view: MovieListView,
    private val favoritesRepository: FavoritesRepository,
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

    fun removeFromFavorites(movie: MovieViewModel) {
        movie.isFavorite = false
        val selectedMovie = getSelectedMovie(movie)
        Executors.newSingleThreadExecutor().execute{ favoritesRepository.deleteFavorite(selectedMovie) }
    }

    fun saveProgress(movie: MovieViewModel, watched: Double) {
        Executors.newSingleThreadExecutor().execute { keepWatchingRepository.saveProgress(movie, watched) }
    }

    private fun setViewModel(movieList: List<Movie>) {
        val viewModels = movieList.map { movie ->
            presenter.convertModel(movie)
        }

        Executors.newSingleThreadExecutor().execute {
            val favorites = favoritesRepository.returnFavorites()
            val watchingList = keepWatchingRepository.returnKeepWatchingList()

            viewModels.map { movie ->
                if (favorites.find { it.id == movie.id } != null) movie.isFavorite = true
                val selected = watchingList.find { it.id == movie.id }
                if (selected != null) movie.progress = selected.progress
            }

            view.setViewModel(getWatchingList(viewModels))
            view.hideProgressBar()
        }
    }

    private fun getWatchingList(viewModels: List<MovieViewModel>): List<MovieViewModel> {
        return viewModels.filter { it.progress in 10..90 }
    }

    private fun getSelectedMovie(movie: MovieViewModel): Movie {
        val movieId = movie.id
        return localMovieStorage.getMovieById(movieId)
    }
}