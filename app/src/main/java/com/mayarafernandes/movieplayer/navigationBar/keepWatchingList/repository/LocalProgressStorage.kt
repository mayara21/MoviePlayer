package com.mayarafernandes.movieplayer.navigationBar.keepWatchingList.repository

import com.mayarafernandes.movieplayer.navigationBar.keepWatchingList.ProgressModel

interface LocalProgressStorage {
    fun saveProgress(movie: ProgressModel)
    fun getProgressPercentage(movieId: String): Int
    fun getMoviesProgressList(): List<ProgressModel>
}
