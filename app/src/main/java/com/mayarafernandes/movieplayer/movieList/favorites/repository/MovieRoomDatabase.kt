package com.mayarafernandes.movieplayer.movieList.favorites.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import com.mayarafernandes.movieplayer.ProgressDao
import com.mayarafernandes.movieplayer.R
import com.mayarafernandes.movieplayer.RoomProgressModel
import com.mayarafernandes.movieplayer.movieList.favorites.repository.roomConverters.*

@Database(entities = [RoomMovieModel::class, RoomProgressModel::class], exportSchema = false, version = 2)
@TypeConverters(ContentsConverter::class, CategoriesConverter::class, CreditsConverter::class, ImagesConverter::class, MovieMetadatasConverter::class, ParentalRatingsConverter::class)
abstract class MovieRoomDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun progressDao(): ProgressDao

    companion object {
        private var INSTANCE: MovieRoomDatabase? = null

        fun getInstance(): MovieRoomDatabase {
            return INSTANCE?: throw IllegalStateException("Must Initialize Room on Application")
        }

        fun init(applicationContext: Context) {
            INSTANCE = Room.databaseBuilder(
                applicationContext,
                MovieRoomDatabase::class.java,
                applicationContext.getString(R.string.room_database_name)
            ).fallbackToDestructiveMigration().build()
        }
    }
}