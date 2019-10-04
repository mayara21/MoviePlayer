package com.mayarafernandes.movieplayer

import com.mayarafernandes.movieplayer.navigationBar.keepWatchingList.KeepWatchingListController
import com.mayarafernandes.movieplayer.navigationBar.keepWatchingList.repository.KeepWatchingRepository
import com.mayarafernandes.movieplayer.navigationBar.movies.repository.MovieRepository
import com.mayarafernandes.movieplayer.navigationBar.movies.repository.storage.LocalMovieStorage
import com.mayarafernandes.movieplayer.navigationBar.movies.view.MovieListPresenter
import com.mayarafernandes.movieplayer.navigationBar.movies.view.MovieListView
import com.mayarafernandes.movieplayer.navigationBar.movies.view.MovieViewModel
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

class KeepWatchingListControllerTest {

    private val movieRepository = mock<MovieRepository>()
    private val presenter = mock<MovieListPresenter>()
    private val view = mock<MovieListView>()
    private val keepWatchingRepository = mock<KeepWatchingRepository>()
    private val localMovieStorage = mock<LocalMovieStorage>()
    private val controller = KeepWatchingListController(movieRepository, presenter, view, keepWatchingRepository, localMovieStorage)

    @Test
    fun `test when call saveProgress expect call save progress in repository`() {
        val watched = 4000.0
        val movie = MovieViewModel(
            "title",
            "description",
            "",
            "id",
            false,
            0
        )

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