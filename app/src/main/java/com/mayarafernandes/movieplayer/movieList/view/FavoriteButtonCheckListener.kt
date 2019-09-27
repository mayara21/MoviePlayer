package com.mayarafernandes.movieplayer.movieList.view

import android.widget.ToggleButton

interface FavoriteButtonCheckListener {
    fun onCheckedChange(
        movie: MovieViewModel,
        favoriteButton: ToggleButton
    )

    fun onUncheckedChange(
        movie: MovieViewModel,
        favoriteButton: ToggleButton
    )
}