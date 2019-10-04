package com.mayarafernandes.movieplayer.navigationBar.favorites

import com.mayarafernandes.movieplayer.navigationBar.movies.repository.Movie

interface FavoritesRepository {

    fun returnFavorites(): List<Movie>
    fun saveFavorite(movie: Movie)
    fun deleteFavorite(movie: Movie)
}