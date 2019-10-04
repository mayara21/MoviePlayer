package com.mayarafernandes.movieplayer.navigationBar.movies.repository

import com.mayarafernandes.movieplayer.navigationBar.movies.repository.service.MovieCallbacks
import com.mayarafernandes.movieplayer.navigationBar.movies.repository.service.MovieService
import com.mayarafernandes.movieplayer.navigationBar.movies.repository.storage.LocalMovieStorage

class MovieRepositoryImpl (private val localMovieStorage: LocalMovieStorage, private val movieService: MovieService): MovieRepository {

    override fun returnMovieList(movieCallbacks: MovieCallbacks) {
        movieService.returnMovieList(object: MovieCallbacks {
            override fun onSuccess(movieList: List<Movie>) {
                localMovieStorage.saveMovieList(movieList)
                movieCallbacks.onSuccess(movieList)
            }

            override fun onError() { movieCallbacks.onError() }
        })
    }

    override fun onMovieSelected(movieId: String): Movie {
        return localMovieStorage.getMovieById(movieId)
    }
}
