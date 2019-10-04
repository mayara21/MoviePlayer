package com.mayarafernandes.movieplayer.navigationBar.keepWatchingList

import com.mayarafernandes.movieplayer.navigationBar.movies.view.MovieViewModel

interface WatchClickListener {
    fun onClickPlay(movie: MovieViewModel)
}