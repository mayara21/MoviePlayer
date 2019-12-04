package com.mayarafernandes.movieplayer.navigationBar

import com.mayarafernandes.movieplayer.navigationBar.movies.view.MovieListPresenter
import com.mayarafernandes.movieplayer.navigationBar.keepWatchingList.repository.KeepWatchingRepository
import com.mayarafernandes.movieplayer.navigationBar.favorites.FavoritesRepository
import com.mayarafernandes.movieplayer.navigationBar.keepWatchingList.ProgressModel
import com.mayarafernandes.movieplayer.navigationBar.movies.MovieListController
import com.mayarafernandes.movieplayer.navigationBar.movies.repository.Movie
import com.mayarafernandes.movieplayer.navigationBar.movies.repository.MovieRepository
import com.mayarafernandes.movieplayer.navigationBar.movies.repository.service.MovieCallbacks
import com.mayarafernandes.movieplayer.navigationBar.movies.repository.storage.LocalMovieStorage
import com.mayarafernandes.movieplayer.navigationBar.movies.view.MovieListView
import com.mayarafernandes.movieplayer.navigationBar.movies.view.MovieViewModel
import com.nhaarman.mockito_kotlin.*
import org.junit.Assert.*
import org.junit.Test

class MovieListControllerTest {

    private val movieRepository = mock<MovieRepository>()
    private val presenter = mock<MovieListPresenter>()
    private val view = mock<MovieListView>()
    private val favoritesRepository = mock<FavoritesRepository>()
    private val keepWatchingRepository = mock<KeepWatchingRepository>()
    private val localMovieStorage = mock<LocalMovieStorage>()
    private val controller =
        MovieListController(
            movieRepository,
            presenter,
            view,
            favoritesRepository,
            keepWatchingRepository,
            localMovieStorage
        )

    @Test
    fun `test when call addToFavorites expect movie added to favorites repository`() {
        val movie = setMovieViewModels()[0]
        val movieId = "test123"
        whenever(movieRepository.onMovieSelected(movieId)).thenReturn(setExpectedSelectedMovie())
        controller.addToFavorites(movie)

        verify(favoritesRepository, timeout(70)).saveFavorite(setExpectedSelectedMovie())
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
        verify(keepWatchingRepository, timeout(70)).saveProgress(movie, watched)
    }

    @Test
    fun `test when call onSelectMovie expect call getMovieById`() {
        val movie = MovieViewModel("test", "", "", "test123", false, 0)
        controller.onSelectMovie(movie)
        verify(movieRepository).onMovieSelected("test123")
    }

    @Test
    fun `test when call onViewCreated expect showProgressBar`() {
        controller.onViewCreated()
        verify(view).showProgressBar()
    }

    @Test
    fun `test when call onViewCreated expect returnMovieList from Storage`() {
        controller.onViewCreated()
        verify(localMovieStorage).returnMovieList()
    }

    @Test
    fun `test when call onViewCreated expect call returnMovieList when return list is empty`() {
        whenever(localMovieStorage.returnMovieList()).thenReturn(emptyList())
        controller.onViewCreated()
        verify(movieRepository, times(1)).returnMovieList(any())
    }

    @Test
    fun `test when call onViewCreated expect call returnFavorites when storage list not empty`() {
        whenever(localMovieStorage.returnMovieList()).thenReturn(setFavorites())
        controller.onViewCreated()
        verify(favoritesRepository, timeout(70)).returnFavorites()
    }

    @Test
    fun `test when call onViewCreated expect call returnKeepWatching list when storage list not empty`() {
        whenever(localMovieStorage.returnMovieList()).thenReturn(setFavorites())
        controller.onViewCreated()
        verify(keepWatchingRepository, timeout(70)).returnKeepWatchingList()
    }

    @Test
    fun `test when call onViewCreated expect call convertModel when storage list not empty`() {
        whenever(localMovieStorage.returnMovieList()).thenReturn(setMovies())
        controller.onViewCreated()
        verify(presenter).convertModel(setMovies()[0])
    }

    @Test
    fun `test when call onViewCreated expect call setViewModel when storage list not empty`() {
        whenever(localMovieStorage.returnMovieList()).thenReturn(setFavorites())
        controller.onViewCreated()
        verify(view, timeout(70)).setViewModel(any())
    }

    @Test
    fun `test when call onViewCreated expect correct argument for view setViewModel when storage list not empty`() {
        val movies = setMovies()
        whenever(localMovieStorage.returnMovieList()).thenReturn(movies)

        whenever(presenter.convertModel(movies[0])).thenReturn(setMovieViewModels()[0])
        whenever(presenter.convertModel(movies[1])).thenReturn(setMovieViewModels()[1])
        whenever(favoritesRepository.returnFavorites()).thenReturn(setFavorites())
        whenever(keepWatchingRepository.returnKeepWatchingList()).thenReturn(setWatching())

        controller.onViewCreated()
        verify(view, timeout(70)).setViewModel(setExpectedViewModels())
    }

    @Test
    fun `test when call onViewCreated expect call hideProgressBar when storage list not empty`() {
        whenever(localMovieStorage.returnMovieList()).thenReturn(setFavorites())
        controller.onViewCreated()
        verify(view, timeout(70)).hideProgressBar()
    }

    @Test
    fun `test when call onViewCreated expect call returnFavorites when storage list empty and success`() {
        whenever(localMovieStorage.returnMovieList()).thenReturn(emptyList())
        setCallbackSuccess()
        controller.onViewCreated()
        verify(favoritesRepository, timeout(70)).returnFavorites()
    }

    @Test
    fun `test when call onViewCreated expect call returnKeepWatching list when storage list empty and success`() {
        whenever(localMovieStorage.returnMovieList()).thenReturn(emptyList())
        setCallbackSuccess()
        controller.onViewCreated()
        verify(keepWatchingRepository, timeout(70)).returnKeepWatchingList()
    }

    @Test
    fun `test when call onViewCreated expect call convertModel when storage list empty and success`() {
        whenever(localMovieStorage.returnMovieList()).thenReturn(emptyList())
        setCallbackSuccess()
        controller.onViewCreated()
        verify(presenter).convertModel(setExpectedSelectedMovie())
    }

    @Test
    fun `test when call onViewCreated expect call setViewModel when storage list empty and success`() {
        whenever(localMovieStorage.returnMovieList()).thenReturn(emptyList())
        setCallbackSuccess()
        controller.onViewCreated()
        verify(view, timeout(70)).setViewModel(any())
    }

    @Test
    fun `test when call onViewCreated expect correct argument for view setViewModel when storage list empty and success`() {
        whenever(localMovieStorage.returnMovieList()).thenReturn(emptyList())
        setCallbackSuccess()
        val movies = setMovies()

        whenever(presenter.convertModel(movies[0])).thenReturn(setMovieViewModels()[0])
        whenever(presenter.convertModel(movies[1])).thenReturn(setMovieViewModels()[1])
        whenever(favoritesRepository.returnFavorites()).thenReturn(setFavorites())
        whenever(keepWatchingRepository.returnKeepWatchingList()).thenReturn(setWatching())

        controller.onViewCreated()
        verify(view, timeout(70)).setViewModel(setExpectedViewModels())
    }

    @Test
    fun `test when call onViewCreated expect call hideProgressBar when storage list empty and success`() {
        whenever(localMovieStorage.returnMovieList()).thenReturn(emptyList())
        setCallbackSuccess()
        controller.onViewCreated()
        verify(view, timeout(70)).hideProgressBar()
    }

    @Test
    fun `test when call onViewCreated expect hideProgressBar when storage list empty and fail`() {
        whenever(localMovieStorage.returnMovieList()).thenReturn(emptyList())
        setCallbackError()
        controller.onViewCreated()
        verify(view).hideProgressBar()
    }

    @Test
    fun `test when call onViewCreated expect hideMovieList when fail`() {
        whenever(localMovieStorage.returnMovieList()).thenReturn(emptyList())
        setCallbackError()
        controller.onViewCreated()
        verify(view).hideMovieList()
    }

    private fun setCallbackSuccess() {
        val movies = setMovies()

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

    private fun setMovies() = listOf(
        Movie(
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
        ),
        Movie(
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
        ))

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

    private fun setWatching() = listOf(ProgressModel(
        "test123",
        50
    ))

    private fun setExpectedViewModels() = listOf(
        MovieViewModel(
            "test",
            "description",
            "url",
            "test123",
            false,
            50
        ),
        MovieViewModel(
        "test",
        "description",
        "",
        "test456",
        true,
        0
    ))
}