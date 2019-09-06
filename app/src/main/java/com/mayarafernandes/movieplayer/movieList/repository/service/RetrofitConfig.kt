package com.mayarafernandes.movieplayer.movieList.repository.service

import com.google.gson.Gson
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