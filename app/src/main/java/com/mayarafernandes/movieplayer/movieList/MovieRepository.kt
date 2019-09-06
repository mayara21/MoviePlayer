package com.mayarafernandes.movieplayer.movieList

import com.mayarafernandes.movieplayer.movieList.service.MovieCallbacks
import com.mayarafernandes.movieplayer.movieList.service.MovieServiceIMPL
import com.mayarafernandes.movieplayer.movieList.storage.LocalMovieStorage

class MovieRepository (private val localMovieStorage: LocalMovieStorage) {
    private val movieService = MovieServiceIMPL()

    fun returnMovieList(movieCallbacks: MovieCallbacks) {
        movieService.returnMovieList(object: MovieCallbacks {
            override fun onSuccess(movieList: List<Movie>) {
                localMovieStorage.saveMovieList(movieList)
                movieCallbacks.onSuccess(movieList)
            }

            override fun onError() { movieCallbacks.onError() }

        })
    }

    fun onMovieSelected(movieId: String): Movie {
        return localMovieStorage.getMovieById(movieId)
    }
}
