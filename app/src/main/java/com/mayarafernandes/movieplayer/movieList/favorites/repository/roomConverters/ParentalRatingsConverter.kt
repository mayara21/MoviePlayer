package com.mayarafernandes.movieplayer.movieList.favorites.repository.roomConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mayarafernandes.movieplayer.movieList.repository.ParentalRating
import java.lang.reflect.Type

class ParentalRatingsConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromString(data: String): List<ParentalRating> {

        val listType: Type = object: TypeToken<List<ParentalRating>>() {}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun toString(objects: List<ParentalRating>): String {
        return gson.toJson(objects)
    }
}