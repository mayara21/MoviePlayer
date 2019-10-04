package com.mayarafernandes.movieplayer.navigationBar.movies.repository.service

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mayarafernandes.movieplayer.navigationBar.movies.repository.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieServiceIMPL: MovieService {
    private val apiEndpoint = "https://private-62c15f-movies295.apiary-mock.com/"

    override fun returnMovieList(movieServiceCallback: MovieCallbacks) {
        var movieServiceList: List<MovieDTO>
        var movieList: List<Movie>

        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        val movieService = RetrofitConfig(
            apiEndpoint,
            gson
        ).buildService(MovieServiceAccess::class.java)
        val requestCall: Call<List<MovieDTO>> = movieService.returnMovieList()

        requestCall.enqueue(object: Callback<List<MovieDTO>> {
            override fun onFailure(call: Call<List<MovieDTO>>, t: Throwable) {
                t.printStackTrace()
                movieServiceCallback.onError()
            }

            override fun onResponse(
                call: Call<List<MovieDTO>>,
                response: Response<List<MovieDTO>>
            ) {
                if(response.isSuccessful) {
                    movieServiceList = response.body()!!
                    movieList = movieServiceList.map { movieDTO -> convertDTOToMovieModel(movieDTO) }
                    movieServiceCallback.onSuccess(movieList)
                    return
                }

                movieServiceCallback.onError()
            }
        })
    }

    private fun convertDTOToMovieModel(movie: MovieDTO) =
        Movie(
            movie.title,
            movie.description,
            movie.type,
            movie.publishedDate,
            movie.availableDate,
            movie.metadata,
            movie.contents,
            movie.credits,
            movie.parentalRatings,
            movie.images,
            movie.categories,
            movie.id
        )
}
