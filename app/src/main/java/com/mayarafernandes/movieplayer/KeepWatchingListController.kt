package com.mayarafernandes.movieplayer

import com.mayarafernandes.movieplayer.movieList.view.MovieViewModel

class KeepWatchingListController(private val keepWatchingRepository: KeepWatchingRepository) {

    fun saveProgress(movie: MovieViewModel, watched: Double) {
        keepWatchingRepository.saveProgress(movie, watched)
    }
}
