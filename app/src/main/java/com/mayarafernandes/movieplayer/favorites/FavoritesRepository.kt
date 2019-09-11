package com.mayarafernandes.movieplayer.favorites

import com.mayarafernandes.movieplayer.movieList.repository.Movie
import com.mayarafernandes.movieplayer.favorites.repository.LocalFavoritesStorage

class FavoritesRepository(private val localStorage: LocalFavoritesStorage) {

    fun returnFavorites(): List<Movie> {
        return localStorage.getFavoriteMovieList()
    }

    fun saveFavorite(movie: Movie) {
        localStorage.insertMovie(movie)
    }

    fun deleteFavorite(movie: Movie) {
        localStorage.deleteMovie(movie)
    }
}