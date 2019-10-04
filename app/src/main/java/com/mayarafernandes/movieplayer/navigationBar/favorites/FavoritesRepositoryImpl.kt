package com.mayarafernandes.movieplayer.navigationBar.favorites

import com.mayarafernandes.movieplayer.navigationBar.movies.repository.Movie
import com.mayarafernandes.movieplayer.navigationBar.favorites.repository.LocalFavoritesStorage

class FavoritesRepositoryImpl(private val localStorage: LocalFavoritesStorage): FavoritesRepository {

    override fun returnFavorites(): List<Movie> {
        return localStorage.getFavoriteMovieList()
    }

    override fun saveFavorite(movie: Movie) {
        localStorage.insertMovie(movie)
    }

    override fun deleteFavorite(movie: Movie) {
        localStorage.deleteMovie(movie)
    }
}