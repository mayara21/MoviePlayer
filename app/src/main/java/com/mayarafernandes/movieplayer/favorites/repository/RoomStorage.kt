package com.mayarafernandes.movieplayer.favorites.repository

import com.mayarafernandes.movieplayer.movieList.repository.Movie

class RoomStorage(private val movieDao: MovieDao):
    LocalFavoritesStorage {

    override fun getFavoriteMovieList(): List<Movie> {
        return movieDao.getFavoriteMovieList().map { movie -> convertToMovieModel(movie) }
    }

    override fun insertMovie(movie: Movie) {
        movieDao.addFavoriteMovie(convertToRoomModel(movie))
    }

    override fun deleteMovie(movie: Movie) {
        movieDao.deleteMovies(convertToRoomModel(movie))
    }

    private fun convertToRoomModel(movie: Movie) =
        RoomMovieModel(
            movie.id,
            movie.title,
            movie.description,
            movie.type,
            movie.publishedDate,
            movie.availableDate,
            movie.metadata,
            movie.contents,
            movie.credits,
            movie.parentalRatings,
            movie.images,
            movie.categories
        )

    private fun convertToMovieModel(movie: RoomMovieModel) =
        Movie(
            movie.title,
            movie.description,
            movie.type,
            movie.publishedDate,
            movie.availableDate,
            movie.metadata,
            movie.contents,
            movie.credits,
            movie.parentalRatings,
            movie.images,
            movie.categories,
            movie.id
        )
}