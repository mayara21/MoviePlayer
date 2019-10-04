package com.mayarafernandes.movieplayer.navigationBar.favorites.repository

import androidx.room.*

@Dao
interface MovieDao {
    @Query("SELECT * FROM favorite_movie")
    fun getFavoriteMovieList(): List<RoomMovieModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavoriteMovie(movie: RoomMovieModel)

    @Update
    fun updateMovies(vararg movies: RoomMovieModel)

    @Delete
    fun deleteMovies(vararg movies: RoomMovieModel)
}