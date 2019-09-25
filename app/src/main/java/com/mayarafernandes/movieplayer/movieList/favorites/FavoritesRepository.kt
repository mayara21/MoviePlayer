package com.mayarafernandes.movieplayer.movieList.favorites

import com.mayarafernandes.movieplayer.movieList.repository.Movie

interface FavoritesRepository {

    fun returnFavorites(): List<Movie>
    fun saveFavorite(movie: Movie)
    fun deleteFavorite(movie: Movie)
}