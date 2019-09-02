package com.mayarafernandes.movieplayer.movieList

import com.mayarafernandes.movieplayer.movieList.view.MovieViewModel

class MovieListController(
    private val movieRepository: MovieRepository,
    private val presenter: MovieListPresenter,
    private val view: MovieListView
) {

    fun onViewCreated() {
        val movieList = movieRepository.returnMovieList()
        val viewModels = movieList.map { movie ->
            presenter.convertModel(movie)
        }

        view.setViewModel(viewModels)
    }

    fun onSelectMovie(movie: MovieViewModel) {
        val movieId = movie.id
        val selectedMovie = movieRepository.onMovieSelected(movieId)
    }
}