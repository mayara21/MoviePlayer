package com.mayarafernandes.movieplayer.movieList

import com.mayarafernandes.movieplayer.KeepWatchingRepository
import com.mayarafernandes.movieplayer.movieList.favorites.FavoritesRepository
import com.mayarafernandes.movieplayer.movieList.repository.Movie
import com.mayarafernandes.movieplayer.movieList.repository.MovieRepository
import com.mayarafernandes.movieplayer.movieList.repository.service.MovieCallbacks
import com.mayarafernandes.movieplayer.movieList.repository.storage.LocalMovieStorage
import com.mayarafernandes.movieplayer.movieList.view.MovieListPresenter
import com.mayarafernandes.movieplayer.movieList.view.MovieListView
import com.mayarafernandes.movieplayer.movieList.view.MovieViewModel
import com.nhaarman.mockito_kotlin.*
import org.junit.Assert.*
import org.junit.Test

class MovieListControllerTest {

    private val movieRepository = mock<MovieRepository>()
    private val presenter = mock<MovieListPresenter>()
    private val view = mock<MovieListView>()
    private val favoritesRepository = mock<FavoritesRepository>()
    private val keepWatchingRepository = mock<KeepWatchingRepository>()
    private val localMovieListView = mock<LocalMovieStorage>()
    private val controller = MovieListController(movieRepository, presenter, view, favoritesRepository, keepWatchingRepository)

    @Test
    fun `test when call getFavorites expect favorites`() {
        val movies = setMovieViewModels()
        val expected = listOf(setExpectedFavorite())
        val result = controller.getFavorites(movies)

        assertEquals(expected, result)
    }

    @Test
    fun `test when call addToFavorites expect movie added to favorites repository`() {
        val movie = setMovieViewModels()[0]
        val movieId = "test123"
        whenever(movieRepository.onMovieSelected(movieId)).thenReturn(setExpectedSelectedMovie())
        controller.addToFavorites(movie)

        verify(favoritesRepository).saveFavorite(setExpectedSelectedMovie())
    }

    @Test
    fun `test when call addToFavorites expect isFavorite true`() {
        val movie = setMovieViewModels()[0]
        controller.addToFavorites(movie)

        assertTrue(movie.isFavorite)
    }

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

    @Test
    fun `test when call onViewCreated expect showProgressBar`() {
        controller.onViewCreated()

        verify(view).showProgressBar()
    }

    @Test
    fun `test when call onViewCreated expect call returnMovieList when success or fail`() {
        controller.onViewCreated()
        verify(movieRepository, times(1)).returnMovieList(any())
    }

    @Test
    fun `test when call onViewCreated expect call returnFavorites when success`() {
        setCallbackSuccess()
        controller.onViewCreated()
        verify(favoritesRepository, timeout(70)).returnFavorites()
    }

    @Test
    fun `test when call onViewCreated expect call convertModel when success`() {
        setCallbackSuccess()
        controller.onViewCreated()
        verify(presenter).convertModel(setExpectedSelectedMovie())
    }

    @Test
    fun `test when call onViewCreated expect call setViewModel when success`() {
        setCallbackSuccess()
        controller.onViewCreated()
        verify(view, timeout(70)).setViewModel(any())
    }

    @Test
    fun `test when call onViewCreated expect correct argument for setViewModel when success`() {
        setCallbackSuccess()

        whenever(presenter.convertModel(any())).thenReturn(setMovieViewModels()[1])
        whenever(favoritesRepository.returnFavorites()).thenReturn(setFavorites())

        controller.onViewCreated()
        verify(view, timeout(70)).setViewModel(setExpectedViewModels())
    }

    @Test
    fun `test when call onViewCreated expect call hideProgressBar when success`() {
        setCallbackSuccess()
        controller.onViewCreated()
        verify(view).hideProgressBar()
    }

    @Test
    fun `test when call onViewCreated expect hideProgressBar when fail`() {
        setCallbackError()
        controller.onViewCreated()
        verify(view).hideProgressBar()
    }

    @Test
    fun `test when call onViewCreated expect hideMovieList when fail`() {
        setCallbackError()
        controller.onViewCreated()
        verify(view).hideMovieList()
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