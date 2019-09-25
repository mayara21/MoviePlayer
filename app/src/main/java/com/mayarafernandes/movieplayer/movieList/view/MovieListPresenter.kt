package com.mayarafernandes.movieplayer.movieList.view

import com.mayarafernandes.movieplayer.movieList.repository.Movie

interface MovieListPresenter {

    fun convertModel(movie: Movie): MovieViewModel
}