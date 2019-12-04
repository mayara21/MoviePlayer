package com.mayarafernandes.movieplayer.navigationBar.movies

import com.mayarafernandes.movieplayer.Navigator
import com.mayarafernandes.movieplayer.PlayerMediaModel
import com.mayarafernandes.movieplayer.navigationBar.keepWatchingList.repository.KeepWatchingRepository
import com.mayarafernandes.movieplayer.navigationBar.favorites.FavoritesRepository
import com.mayarafernandes.movieplayer.navigationBar.movies.repository.Movie
import com.mayarafernandes.movieplayer.navigationBar.movies.repository.MovieRepository
import com.mayarafernandes.movieplayer.navigationBar.movies.repository.service.MovieCallbacks
import com.mayarafernandes.movieplayer.navigationBar.movies.repository.storage.LocalMovieStorage
import com.mayarafernandes.movieplayer.navigationBar.movies.view.MovieListPresenter
import com.mayarafernandes.movieplayer.navigationBar.movies.view.MovieListView
import com.mayarafernandes.movieplayer.navigationBar.movies.view.MovieViewModel
import java.util.concurrent.Executors

class MovieListController(
    private val movieRepository: MovieRepository,
    private val presenter: MovieListPresenter,
    private val view: MovieListView,
    private val favoritesRepository: FavoritesRepository,
    private val keepWatchingRepository: KeepWatchingRepository,
    private val localMovieStorage: LocalMovieStorage,
    private val navigator: Navigator
) {

    fun onViewCreated() {
        view.showProgressBar()

        val movies = localMovieStorage.returnMovieList()

        if(movies.isEmpty()) {
            movieRepository.returnMovieList(object: MovieCallbacks {
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
        val selectedMovie = getSelectedMovie(movie)
        val content = selectedMovie.contents.find { it.format == "mp4" }

        val playableMovie =
            PlayerMediaModel(
                selectedMovie.id,
                selectedMovie.title,
                selectedMovie.description,
                content!!.url,
                content.duration,
                content.id
                )
        navigator.goToSelectedMovie(playableMovie)
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

    fun saveProgress(movie: MovieViewModel, watched: Double) {
        Executors.newSingleThreadExecutor().execute { keepWatchingRepository.saveProgress(movie, watched) }
    }

    private fun setViewModel(movies: List<Movie>) {
        val viewModels= movies.map { movie ->
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

            view.setViewModel(viewModels)
            view.hideProgressBar()
        }
    }

    private fun getSelectedMovie(movie: MovieViewModel): Movie {
        val movieId = movie.id
        return movieRepository.onMovieSelected(movieId)
    }
}