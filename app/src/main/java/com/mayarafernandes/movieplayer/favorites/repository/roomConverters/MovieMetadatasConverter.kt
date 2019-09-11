package com.mayarafernandes.movieplayer.favorites.repository.roomConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mayarafernandes.movieplayer.movieList.repository.MovieMetadata
import java.lang.reflect.Type

class MovieMetadatasConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromString(data: String): List<MovieMetadata> {

        val listType: Type = object: TypeToken<List<MovieMetadata>>() {}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun toString(objects: List<MovieMetadata>): String {
        return gson.toJson(objects)
    }
}