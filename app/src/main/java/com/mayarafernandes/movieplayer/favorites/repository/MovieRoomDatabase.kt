package com.mayarafernandes.movieplayer.favorites.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mayarafernandes.movieplayer.favorites.repository.roomConverters.*

@Database(entities = [RoomMovieModel::class], exportSchema = false, version = 1)
@TypeConverters(ContentsConverter::class, CategoriesConverter::class, CreditsConverter::class, ImagesConverter::class, MovieMetadatasConverter::class, ParentalRatingsConverter::class)
abstract class MovieRoomDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}