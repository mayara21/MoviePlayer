package com.mayarafernandes.movieplayer.navigationBar.keepWatchingList.repository

import android.util.Log
import com.mayarafernandes.movieplayer.navigationBar.keepWatchingList.ProgressModel
import com.mayarafernandes.movieplayer.navigationBar.movies.repository.storage.LocalMovieStorage
import com.mayarafernandes.movieplayer.navigationBar.movies.view.MovieViewModel

class KeepWatchingRepositoryImpl(private val localProgressStorage: LocalProgressStorage, private val memoryRepository: LocalMovieStorage):
    KeepWatchingRepository {

    override fun saveProgress(movie: MovieViewModel, watched: Double) {
        var progress: Int
        val movies = memoryRepository.returnMovieList()
        val selected = movies.find { it.id == movie.id }
        val duration = selected!!.contents[0].duration

        progress = if(watched > duration) 100
        else ((watched/duration) * 100).toInt()

        Log.d("moviePlayer",  "Duracao: $duration, assistido: $watched, salvando progresso $progress")
        localProgressStorage.saveProgress(
            ProgressModel(
                movie.id,
                progress
            )
        )
    }

    override fun getProgress(movieId: String): Int {
        return localProgressStorage.getProgressPercentage(movieId)
    }

    override fun returnKeepWatchingList(): List<ProgressModel> {
        return localProgressStorage.getMoviesProgressList()
    }
}
