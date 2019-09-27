package com.mayarafernandes.movieplayer.favorites.repository

import androidx.room.*
import com.mayarafernandes.movieplayer.movieList.repository.*

@Entity(tableName = "favorite_movie")
data class RoomMovieModel(
    @PrimaryKey val id: String = "",
    @ColumnInfo val title: String,
    @ColumnInfo val description: String,
    @ColumnInfo val type: String,
    @ColumnInfo val publishedDate: Double,
    @ColumnInfo val availableDate: Double,
    @ColumnInfo val metadata: List<MovieMetadata>,
    @ColumnInfo val contents: List<Content>,
    @ColumnInfo val credits: List<Credit>,
    @ColumnInfo val parentalRatings: List<ParentalRating>,
    @ColumnInfo val images: List<Image>,
    @ColumnInfo val categories: List<Category>
    )