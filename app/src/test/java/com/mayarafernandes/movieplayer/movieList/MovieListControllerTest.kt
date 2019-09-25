package com.mayarafernandes.movieplayer.movieList

import com.mayarafernandes.movieplayer.movieList.favorites.FavoritesRepository
import com.mayarafernandes.movieplayer.movieList.repository.Movie
import com.mayarafernandes.movieplayer.movieList.repository.MovieRepository
import com.mayarafernandes.movieplayer.movieList.view.MovieListPresenter
import com.mayarafernandes.movieplayer.movieList.view.MovieListView
import com.mayarafernandes.movieplayer.movieList.view.MovieViewModel
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert.*
import org.junit.Test

class MovieListControllerTest {

    private val movieRepository = mock<MovieRepository>()
    private val presenter = mock<MovieListPresenter>()
    private val view = mock<MovieListView>()
    private val favoritesRepository = mock<FavoritesRepository>()
    private val controller = MovieListController(movieRepository, presenter, view, favoritesRepository)

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
        whenever(movieRepository.onMovieSelected(movieId)).thenReturn(setExpectedSelectedMovie())
        controller.removeFromFavorites(movie)

        verify(favoritesRepository).deleteFavorite(setExpectedSelectedMovie())
    }

    @Test
    fun `test when call removeFromFavorites expect isFavorite false`() {
        val movie = setMovieViewModels()[0]
        controller.removeFromFavorites(movie)

        assertFalse(movie.isFavorite)
    }

    /*@Test
    fun `test when call onViewCreated expect`() {

    }*/

    private fun setMovieViewModels() = listOf(
        MovieViewModel(
        "test",
        "description",
        "url",
        "test123",
        false
    ), MovieViewModel(
        "test2",
        "description2",
        "url",
        "test456",
        true
    ))

    private fun setExpectedFavorite() = MovieViewModel(
        "test2",
        "description2",
        "url",
        "test456",
        true
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
}