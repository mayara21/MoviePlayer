package com.mayarafernandes.movieplayer.navigationBar.movies.repository.service

import retrofit2.Call
import retrofit2.http.GET

interface MovieServiceAccess {
    @GET("movies")
    fun returnMovieList(): Call<List<MovieDTO>>
}