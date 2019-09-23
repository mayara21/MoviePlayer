package com.mayarafernandes.movieplayer.movieList.view

interface FavoriteButtonCheckListener {
    fun onCheckedChange(movie: MovieViewModel)

    fun onUncheckedChange(movie: MovieViewModel)
}