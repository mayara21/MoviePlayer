package com.mayarafernandes.movieplayer.navigationBar.movies.view

interface MovieListView {
    fun setViewModel(viewModels: List<MovieViewModel>)
    fun showProgressBar()
    fun hideProgressBar()
    fun showMovieList()
    fun hideMovieList()
}
