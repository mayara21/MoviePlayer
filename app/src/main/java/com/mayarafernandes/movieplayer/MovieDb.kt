package com.mayarafernandes.movieplayer

interface MovieDb {
    fun getSize(): Int
    fun getMovieTitle(position: Int): String?
    fun getMovieDescription(position: Int): String?
    fun getMovieImageUrl(position: Int): String?
}