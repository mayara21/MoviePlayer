package com.mayarafernandes.movieplayer.movieList.view

import com.mayarafernandes.movieplayer.movieList.repository.Movie

class MovieListPresenterImpl: MovieListPresenter {

    override fun convertModel(movie: Movie) = MovieViewModel(
        movie.title,
        movie.description,
        movie.images.find { it.type == "cover" }!!.url,
        movie.id,
        false,
        0)
}