package com.mayarafernandes.movieplayer

import com.mayarafernandes.movieplayer.movieList.repository.Movie
import com.mayarafernandes.movieplayer.movieList.view.MovieViewModel

interface LocalProgressStorage {
    fun saveProgress(movie: ProgressModel)
    fun getProgressPercentage(movieId: String): Int
    fun getMoviesProgressList(): List<ProgressModel>
}
