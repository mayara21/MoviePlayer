package com.mayarafernandes.movieplayer

import com.mayarafernandes.movieplayer.movieList.repository.storage.LocalMovieStorage
import com.mayarafernandes.movieplayer.movieList.view.MovieViewModel

class KeepWatchingRepositoryImpl(private val localProgressStorage: LocalProgressStorage, private val memoryRepository: LocalMovieStorage): KeepWatchingRepository {

    override fun saveProgress(movie: MovieViewModel, watched: Double) {
        val movies = memoryRepository.returnMovieList()
        val selected = movies.find { it.id == movie.id }
        val duration = selected!!.contents[0].duration
        val progress = ((watched/duration) * 100).toInt()

        localProgressStorage.saveProgress(ProgressModel(movie.id, progress))
    }

    override fun getProgress(movieId: String): Int {
        return localProgressStorage.getProgressPercentage(movieId)
    }

    override fun returnKeepWatchingList(): List<ProgressModel> {
        return localProgressStorage.getMoviesProgressList()
    }
}
