package com.mayarafernandes.movieplayer.movieList

import com.mayarafernandes.movieplayer.movieList.view.MovieViewModel

class MovieListPresenter {

    fun convertModel(movie: Movie): MovieViewModel {

        val viewModelItem = MovieViewModel()

        viewModelItem.title = movie.title
        viewModelItem.description = movie.description
        viewModelItem.urlImage = movie.urlImage

        return viewModelItem
    }
}