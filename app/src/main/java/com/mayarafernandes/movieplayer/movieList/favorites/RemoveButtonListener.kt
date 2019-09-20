package com.mayarafernandes.movieplayer.movieList.favorites

import com.mayarafernandes.movieplayer.movieList.view.MovieViewModel

interface RemoveButtonListener {
    fun onClickRemove(
        movie: MovieViewModel
    )
}