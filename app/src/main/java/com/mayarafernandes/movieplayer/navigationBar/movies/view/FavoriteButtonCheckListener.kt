package com.mayarafernandes.movieplayer.navigationBar.movies.view

interface FavoriteButtonCheckListener {
    fun onCheckedChange(movie: MovieViewModel)

    fun onUncheckedChange(movie: MovieViewModel)
}