package com.mayarafernandes.movieplayer

import com.mayarafernandes.movieplayer.movieList.view.MovieViewModel

interface KeepWatchingRepository {
    fun saveProgress(movie: MovieViewModel, watched: Double)
    fun getProgress(movieId: String): Int
    fun returnKeepWatchingList(): List<ProgressModel>
}