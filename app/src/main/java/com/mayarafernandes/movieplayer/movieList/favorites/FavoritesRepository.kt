package com.mayarafernandes.movieplayer.movieList.favorites

import android.util.Log
import com.mayarafernandes.movieplayer.movieList.repository.Movie
import com.mayarafernandes.movieplayer.movieList.favorites.repository.LocalFavoritesStorage

class FavoritesRepository(private val localStorage: LocalFavoritesStorage) {

    fun returnFavorites(): List<Movie> {
        return localStorage.getFavoriteMovieList()
    }

    fun saveFavorite(movie: Movie) {
        localStorage.insertMovie(movie)
        val name = movie.title
        Log.d("moviePlayer", "salvei $name!")
    }

    fun deleteFavorite(movie: Movie) {
        localStorage.deleteMovie(movie)
    }
}