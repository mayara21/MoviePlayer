package com.mayarafernandes.movieplayer

import com.mayarafernandes.movieplayer.movieList.view.MovieViewModel

class KeepWatchingRepository(private val localProgressStorage: LocalProgressStorage) {
    fun saveProgress(movie: MovieViewModel, progress: Double, percentage: Double) {
        localProgressStorage.saveProgress(movie, progress, percentage)
    }

    fun getProgress(movie: MovieViewModel): Double {
        return localProgressStorage.getProgressPercentage(movie)
    }

    fun returnKeepWatchingList(): List<MovieViewModel> {
        return localProgressStorage.getMoviesProgressList()
    }

}
