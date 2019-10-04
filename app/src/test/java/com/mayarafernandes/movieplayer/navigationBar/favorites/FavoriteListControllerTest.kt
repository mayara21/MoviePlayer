package com.mayarafernandes.movieplayer.navigationBar.favorites

import com.mayarafernandes.movieplayer.navigationBar.keepWatchingList.repository.KeepWatchingRepository
import com.mayarafernandes.movieplayer.navigationBar.movies.repository.Movie
import com.mayarafernandes.movieplayer.navigationBar.movies.repository.MovieRepository
import com.mayarafernandes.movieplayer.navigationBar.movies.repository.service.MovieCallbacks
import com.mayarafernandes.movieplayer.navigationBar.movies.repository.storage.LocalMovieStorage
import com.mayarafernandes.movieplayer.navigationBar.movies.view.MovieListPresenter
import com.mayarafernandes.movieplayer.navigationBar.movies.view.MovieListView
import com.mayarafernandes.movieplayer.navigationBar.movies.view.MovieViewModel
import com.nhaarman.mockito_kotlin.*
import org.junit.Assert.*
import org.junit.Test

class FavoriteListControllerTest {

    private val movieRepository = mock<MovieRepository>()
    private val presenter = mock<MovieListPresenter>()
    private val view = mock<MovieListView>()
    private val favoritesRepository = mock<FavoritesRepository>()
    private val keepWatchingRepository = mock<KeepWatchingRepository>()
    private val localMovieStorage = mock<LocalMovieStorage>()
    private val controller = FavoriteListController(movieRepository, presenter, view, favoritesRepository, keepWatchingRepository, localMovieStorage)

    @Test
    fun `test when call removeFromFavorites expect movie removed from favorites repository`() {
        val movie = setMovieViewModels()[1]
        val movieId = "test456"
        whenever(movieRepository.onMovieSelected(movieId)).thenReturn(setExpectSelectedMovieFavorite())
        controller.removeFromFavorites(movie)
        verify(favoritesRepository, timeout(70)).deleteFavorite(setExpectSelectedMovieFavorite())
    }

    @Test
    fun `test when call removeFromFavorites expect isFavorite false`() {
        val movie = setMovieViewModels()[0]
        controller.removeFromFavorites(movie)

        assertFalse(movie.isFavorite)
    }

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

    private fun setCallbackSuccess() {
        val movies = listOf(setExpectedSelectedMovie())

        doAnswer {
            val callback = it.arguments[0] as MovieCallbacks
            callback.onSuccess(movies)
            return@doAnswer null
        }.whenever(movieRepository).returnMovieList(any())
    }

    private fun setCallbackError() {
        doAnswer {
            val callback = it.arguments[0] as MovieCallbacks
            callback.onError()
            return@doAnswer null
        }.whenever(movieRepository).returnMovieList(any())
    }

    private fun setMovieViewModels() = listOf(
        MovieViewModel(
            "test",
            "description",
            "url",
            "test123",
            false,
            0
        ), MovieViewModel(
            "test",
            "description",
            "",
            "test456",
            true,
            0
        ))

    private fun setExpectedFavorite() = MovieViewModel(
        "test",
        "description",
        "",
        "test456",
        true,
        0
    )

    private fun setExpectedSelectedMovie() = Movie(
        "test",
        "description",
        "type",
        0.0,
        0.0,
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        "test123",
        false
    )

    private fun setExpectSelectedMovieFavorite() = Movie(
        "test",
        "description",
        "type",
        0.0,
        0.0,
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        "test456",
        true
    )

    private fun setFavorites() = listOf(setExpectSelectedMovieFavorite())

    private fun setExpectedViewModels() = listOf(MovieViewModel(
        "test",
        "description",
        "",
        "test456",
        true,
        0
    ))
}