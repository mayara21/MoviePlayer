package com.mayarafernandes.movieplayer.movieList

import com.mayarafernandes.movieplayer.movieList.service.TempService
import com.mayarafernandes.movieplayer.movieList.storage.LocalMovieStorage

class MovieRepository (private val localMovieStorage: LocalMovieStorage) {
    private val tempService = TempService()

    fun returnMovieList(): List<Movie> {
        val movieList = tempService.returnMovieList()
        localMovieStorage.saveMovieList(movieList)

        return movieList
    }

    fun onMovieSelected(movieId: Int): Movie {
        return localMovieStorage.getMovieById(movieId)
    }

}