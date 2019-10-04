package com.mayarafernandes.movieplayer.navigationBar.favorites.repository

import com.mayarafernandes.movieplayer.navigationBar.movies.repository.Movie

interface LocalFavoritesStorage {

    fun getFavoriteMovieList(): List<Movie>
    fun insertMovie(movie: Movie)
    fun deleteMovie(movie: Movie)
}