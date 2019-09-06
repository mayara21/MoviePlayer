package com.mayarafernandes.movieplayer.movieList

import com.google.gson.Gson
import com.mayarafernandes.movieplayer.movieList.service.MovieService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig(apiEndpoint:String, gson: Gson) {

    private val okHttp = OkHttpClient.Builder()

    private val retrofit = Retrofit.Builder()
        .baseUrl(apiEndpoint)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttp.build())
        .build()

    fun<T> buildService(serviceType: Class<T>): T {
        return this.retrofit.create(serviceType)
    }
}