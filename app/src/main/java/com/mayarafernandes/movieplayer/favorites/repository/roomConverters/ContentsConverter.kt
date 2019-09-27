package com.mayarafernandes.movieplayer.favorites.repository.roomConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mayarafernandes.movieplayer.movieList.repository.Content
import java.lang.reflect.Type

class ContentsConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromString(data: String): List<Content> {

        val listType: Type = object: TypeToken<List<Content>>() {}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun toString(objects: List<Content>): String {
        return gson.toJson(objects)
    }
}