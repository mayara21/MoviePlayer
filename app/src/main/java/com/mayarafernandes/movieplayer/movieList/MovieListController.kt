package com.mayarafernandes.movieplayer.movieList

import android.view.View

class MovieListController(
    private val itemMovie: ItemMovie,
    private val presenter: MovieListPresenter,
    private val view: MovieListView
) {

    fun onViewCreated() {
        val movieList = itemMovie.returnMovieList()
        val viewModels = movieList.map { movie ->
            presenter.convertModel(movie)
        }

        view.setViewModel(viewModels)
    }
}