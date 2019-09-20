package com.mayarafernandes.movieplayer.movieList.favorites.repository.roomConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mayarafernandes.movieplayer.movieList.repository.Credit
import java.lang.reflect.Type

class CreditsConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromString(data: String): List<Credit> {

        val listType: Type = object: TypeToken<List<Credit>>() {}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun toString(objects: List<Credit>): String {
        return gson.toJson(objects)
    }
}