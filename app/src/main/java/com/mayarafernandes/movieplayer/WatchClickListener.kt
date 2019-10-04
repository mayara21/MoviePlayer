package com.mayarafernandes.movieplayer

import com.mayarafernandes.movieplayer.movieList.view.MovieViewModel

interface WatchClickListener {
    fun onClickPlay(movie: MovieViewModel)
}