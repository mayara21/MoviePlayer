package com.mayarafernandes.movieplayer.movieList.favorites

import com.mayarafernandes.movieplayer.movieList.favorites.repository.LocalFavoritesStorage
import com.mayarafernandes.movieplayer.movieList.repository.*
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert.*
import org.junit.Test

class FavoritesRepositoryImplTest {

    private val localStorage = mock<LocalFavoritesStorage>()
    private val favoritesRepository = FavoritesRepositoryImpl(localStorage)

    private val movie = setMovie()

    @Test
    fun `test when call saveFavorite expect call save`() {
        favoritesRepository.saveFavorite(movie)
        verify(localStorage).insertMovie(movie)
    }

    @Test
    fun `test when call deleteFavorite expect call delete`() {
        favoritesRepository.deleteFavorite(movie)
        verify(localStorage).deleteMovie(movie)
    }

    @Test
    fun `test when call returnFavorites expect call get favorites list`() {
        favoritesRepository.returnFavorites()
        verify(localStorage).getFavoriteMovieList()
    }

    @Test
    fun `test when call returnFavorites expect expectedFavorites`() {
        whenever(localStorage.getFavoriteMovieList()).thenReturn(setExpectedFavorites())
        val result = favoritesRepository.returnFavorites()

        assertEquals(setExpectedFavorites(), result)
    }

    private fun setExpectedFavorites() = listOf(Movie(
        "filme teste",
        "descricao teste",
        "tipo",
        0.0,
        0.0,
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        "teste123",
        false
    ))

    private fun setMovie() = Movie(
        "filme teste",
        "descricao teste",
        "tipo",
        0.0,
        0.0,
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        "teste123",
        false
    )
}