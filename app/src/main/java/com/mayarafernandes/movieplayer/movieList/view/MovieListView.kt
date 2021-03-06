package com.mayarafernandes.movieplayer.movieList.view

interface MovieListView {
    fun setViewModel(viewModels: List<MovieViewModel>)
    fun showProgressBar()
    fun hideProgressBar()
    fun showMovieList()
    fun hideMovieList()
}
