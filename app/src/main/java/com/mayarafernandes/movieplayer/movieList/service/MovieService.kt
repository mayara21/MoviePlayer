package com.mayarafernandes.movieplayer.movieList.service

import com.mayarafernandes.movieplayer.movieList.Movie
import retrofit2.Call
import retrofit2.http.GET

interface MovieService {
    @GET("movies")
    fun returnMovieList(): Call<List<MovieDTO>>

    //fun returnMovieList(): List<Movie>
}