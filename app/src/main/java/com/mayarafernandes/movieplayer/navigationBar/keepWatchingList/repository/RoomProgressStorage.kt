package com.mayarafernandes.movieplayer.navigationBar.keepWatchingList.repository

import com.mayarafernandes.movieplayer.navigationBar.keepWatchingList.ProgressModel

class RoomProgressStorage(private val progressDao: ProgressDao):
    LocalProgressStorage {

    override fun saveProgress(movie: ProgressModel) {
        progressDao.addProgress(convertToRoomProgressModel(movie))
    }

    override fun getProgressPercentage(movieId: String): Int {
        return progressDao.getProgress(movieId)
    }

    override fun getMoviesProgressList(): List<ProgressModel> {
        return progressDao.getProgressList().map { movie -> convertToProgressModel(movie) }
    }

    private fun convertToRoomProgressModel(movie: ProgressModel) =
        RoomProgressModel(
            movie.id,
            movie.progress
        )

    private fun convertToProgressModel(movie: RoomProgressModel) =
        ProgressModel(
            movie.id,
            movie.progress
        )
}