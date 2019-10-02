package com.mayarafernandes.movieplayer

import androidx.room.*

@Dao
interface ProgressDao {

    @Query("SELECT * FROM movies_progress")
    fun getProgressList(): List<RoomProgressModel>

    @Query("SELECT progress FROM movies_progress WHERE id LIKE :movieId")
    fun getProgress(movieId: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProgress(movie: RoomProgressModel)
}