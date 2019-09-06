package com.mayarafernandes.movieplayer.movieList.view

import com.mayarafernandes.movieplayer.movieList.Movie

class MovieListPresenter {

    fun convertModel(movie: Movie) = MovieViewModel(
        movie.title,
        movie.description,
        movie.images.find { it.type == "cover" }!!.url,
        movie.id)
}