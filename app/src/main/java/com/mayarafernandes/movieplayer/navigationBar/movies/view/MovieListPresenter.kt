package com.mayarafernandes.movieplayer.navigationBar.movies.view

import com.mayarafernandes.movieplayer.navigationBar.movies.repository.Movie

interface MovieListPresenter {

    fun convertModel(movie: Movie): MovieViewModel
}