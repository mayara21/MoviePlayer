package com.mayarafernandes.movieplayer

import com.mayarafernandes.movieplayer.navigationBar.movies.repository.Movie
import com.mayarafernandes.movieplayer.navigationBar.movies.view.MovieViewModel

interface MovieListPresenter {
    fun convertModel(movie: Movie): MovieViewModel
}
