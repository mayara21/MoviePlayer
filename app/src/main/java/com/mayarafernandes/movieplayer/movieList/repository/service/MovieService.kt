package com.mayarafernandes.movieplayer.movieList.service

import retrofit2.Call
import retrofit2.http.GET

interface MovieService {
    @GET("movies")
    fun returnMovieList(): Call<List<MovieDTO>>
}