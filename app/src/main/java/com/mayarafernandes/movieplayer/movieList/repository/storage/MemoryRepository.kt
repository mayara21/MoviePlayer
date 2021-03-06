package com.mayarafernandes.movieplayer.movieList.repository.storage

import com.mayarafernandes.movieplayer.movieList.repository.Movie

class MemoryRepository: LocalMovieStorage {

    var movieList: List<Movie> = emptyList()

    override fun saveMovieList(movieList: List<Movie>) {
        this.movieList = movieList
    }

    override fun returnMovieList(): List<Movie> {
        return movieList
    }

    override fun getMovieById(movieId: String): Movie {
        return movieList.find { it.id == movieId }!!
    }
}