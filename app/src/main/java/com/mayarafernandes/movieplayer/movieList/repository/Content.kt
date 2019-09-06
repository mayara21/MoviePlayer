package com.mayarafernandes.movieplayer.movieList.repository

data class Content(val url: String,
                   val format: String,
                   val width: Int,
                   val height: Int,
                   val language: String,
                   val duration: Double,
                   val geoLock: Boolean,
                   val id: String)
