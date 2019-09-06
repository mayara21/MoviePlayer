package com.mayarafernandes.movieplayer.movieList.service

import com.mayarafernandes.movieplayer.movieList.*

data class MovieDTO(val title: String,
                    val description: String,
                    val type: String,
                    val publishedDate: Double,
                    val availableDate: Double,
                    val metadata: List<MovieMetadata>,
                    val contents: List<Content>,
                    val credits: List<Credit>,
                    val parentalRatings: List<ParentalRating>,
                    val images: List<Image>,
                    val categories: List<Category>,
                    val id: String)