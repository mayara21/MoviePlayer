package com.mayarafernandes.movieplayer.movieList

import com.mayarafernandes.movieplayer.movieList.view.MovieViewModel

interface MovieListView {

    fun setViewModel(viewModels: List<MovieViewModel>)
}
