package com.mayarafernandes.movieplayer.navigationBar.movies.view

import com.mayarafernandes.movieplayer.navigationBar.movies.repository.Movie

class MovieListPresenterImpl:
    MovieListPresenter {

    override fun convertModel(movie: Movie) = MovieViewModel(
        movie.title,
        movie.description,
        movie.images.find { it.type == "cover" }!!.url,
        movie.id,
        false,
        0)
}