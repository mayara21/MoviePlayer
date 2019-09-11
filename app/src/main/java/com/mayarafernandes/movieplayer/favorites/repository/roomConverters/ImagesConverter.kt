package com.mayarafernandes.movieplayer.favorites.repository.roomConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mayarafernandes.movieplayer.movieList.repository.Image
import java.lang.reflect.Type

class ImagesConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromString(data: String): List<Image> {

        val listType: Type = object: TypeToken<List<Image>>() {}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun toString(objects: List<Image>): String {
        return gson.toJson(objects)
    }
}