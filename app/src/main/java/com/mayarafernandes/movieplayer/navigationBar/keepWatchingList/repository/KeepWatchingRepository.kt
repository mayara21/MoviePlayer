package com.mayarafernandes.movieplayer.navigationBar.keepWatchingList.repository

import com.mayarafernandes.movieplayer.navigationBar.keepWatchingList.ProgressModel
import com.mayarafernandes.movieplayer.navigationBar.movies.view.MovieViewModel

interface KeepWatchingRepository {
    fun saveProgress(movie: MovieViewModel, watched: Double)
    fun getProgress(movieId: String): Int
    fun returnKeepWatchingList(): List<ProgressModel>
}