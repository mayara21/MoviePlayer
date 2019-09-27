package com.mayarafernandes.movieplayer.favorites.repository

import com.mayarafernandes.movieplayer.movieList.repository.Movie

interface LocalFavoritesStorage {

    fun getFavoriteMovieList(): List<Movie>
    fun insertMovie(movie: Movie)
    fun deleteMovie(movie: Movie)
}