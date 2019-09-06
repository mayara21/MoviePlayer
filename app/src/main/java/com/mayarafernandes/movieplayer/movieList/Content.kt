package com.mayarafernandes.movieplayer.movieList

data class Content(val url: String,
                   val format: String,
                   val width: Int,
                   val height: Int,
                   val language: String,
                   val duration: Double,
                   val geoLock: Boolean,
                   val id: String)
