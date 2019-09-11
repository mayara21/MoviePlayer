package com.mayarafernandes.movieplayer.favorites.repository.roomConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mayarafernandes.movieplayer.movieList.repository.Category
import java.lang.reflect.Type

class CategoriesConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromString(data: String): List<Category> {

        val listType: Type = object: TypeToken<List<Category>>() {}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun toString(objects: List<Category>): String {
        return gson.toJson(objects)
    }
}