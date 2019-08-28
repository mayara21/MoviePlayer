package com.mayarafernandes.movieplayer

import com.mayarafernandes.movieplayer.movieList.view.Supplier

class TempDb : MovieDb {

    private val movieList = Supplier.movies

    override fun getSize() = movieList.size
    override fun getMovieTitle(position: Int) = movieList[position].title
    override fun getMovieDescription(position: Int) = movieList[position].description
    override fun getMovieImageUrl(position: Int) = movieList[position].urlImage

}