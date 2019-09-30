package com.mayarafernandes.movieplayer.movieList.favorites

import com.mayarafernandes.movieplayer.movieList.repository.Movie
import com.mayarafernandes.movieplayer.movieList.favorites.repository.LocalFavoritesStorage

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