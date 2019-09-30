package com.mayarafernandes.movieplayer

import com.mayarafernandes.movieplayer.movieList.view.MovieViewModel

interface LocalProgressStorage {
    fun saveProgress(movie: MovieViewModel, progress: Double, percentage: Double)
    fun getProgressPercentage(movie: MovieViewModel): Double
    fun getMoviesProgressList(): List<MovieViewModel>
}
