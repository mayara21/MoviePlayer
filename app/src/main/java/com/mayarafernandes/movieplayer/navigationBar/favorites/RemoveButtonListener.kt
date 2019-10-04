package com.mayarafernandes.movieplayer.navigationBar.favorites

import com.mayarafernandes.movieplayer.navigationBar.movies.view.MovieViewModel

interface RemoveButtonListener {
    fun onClickRemove(
        movie: MovieViewModel
    )
}