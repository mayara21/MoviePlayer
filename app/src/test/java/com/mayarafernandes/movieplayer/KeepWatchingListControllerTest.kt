package com.mayarafernandes.movieplayer

import com.mayarafernandes.movieplayer.movieList.repository.Movie
import com.mayarafernandes.movieplayer.movieList.view.MovieListView
import com.mayarafernandes.movieplayer.movieList.view.MovieViewModel
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

class KeepWatchingListControllerTest {

    private val keepWatchingRepository = mock<KeepWatchingRepository>()
    private val controller = KeepWatchingListController(keepWatchingRepository)
    private val movie = setMovieViewModel()
    private val watched = 4000.0

    @Test
    fun `test when call saveProgress expect call saveProgress in repository`() {
        controller.saveProgress(movie, watched)
        verify(keepWatchingRepository).saveProgress(movie, watched)
    }



    private fun setMovieViewModel() = MovieViewModel(
        "title",
        "description",
        "",
        "id",
        false,
        0
    )
}