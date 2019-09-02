package com.mayarafernandes.movieplayer.movieList

import com.mayarafernandes.movieplayer.movieList.view.MovieViewModel

class MovieListPresenter {

    fun convertModel(movie: Movie) = MovieViewModel(
        movie.title,
        movie.description,
        movie.urlImage,
        movie.id)
}