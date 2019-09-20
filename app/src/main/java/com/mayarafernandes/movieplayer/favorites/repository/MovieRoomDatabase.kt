package com.mayarafernandes.movieplayer.favorites.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mayarafernandes.movieplayer.R
import com.mayarafernandes.movieplayer.favorites.repository.roomConverters.*

@Database(entities = [RoomMovieModel::class], exportSchema = false, version = 1)
@TypeConverters(ContentsConverter::class, CategoriesConverter::class, CreditsConverter::class, ImagesConverter::class, MovieMetadatasConverter::class, ParentalRatingsConverter::class)
abstract class MovieRoomDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

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
            ).build()
        }
    }
}