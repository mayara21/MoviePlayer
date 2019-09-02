package com.mayarafernandes.movieplayer.movieList.service

import com.mayarafernandes.movieplayer.movieList.Supplier

class MovieServiceIMPL : MovieService {
    private val movieList = Supplier.movies

    override fun returnMovieList() = movieList
}