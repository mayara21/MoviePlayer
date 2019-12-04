package com.mayarafernandes.movieplayer.navigationBar.movies.view

import com.mayarafernandes.movieplayer.navigationBar.movies.repository.Movie
import com.mayarafernandes.movieplayer.navigationBar.movies.view.MovieViewModel

interface MovieListPresenter {
    fun convertModel(movie: Movie): MovieViewModel
}
