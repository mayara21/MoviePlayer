package com.mayarafernandes.movieplayer.navigationBar.favorites.repository

import com.mayarafernandes.movieplayer.navigationBar.movies.repository.Movie
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert.*
import org.junit.Test

class RoomStorageTest {

    private val movieDao = mock<MovieDao>()
    private val roomStorage = RoomStorage(movieDao)

    @Test
    fun `test when call insertMovie expect insert expectedConvertedRoomMovieModel`() {
        val expected = expectedConvertedRoomMovieModel()
        val movie = setMovieModel()

        roomStorage.insertMovie(movie)
        verify(movieDao).addFavoriteMovie(expected)
    }

    @Test
    fun `test when call deleteMovie expect delete expectedConvertedRoomMovieModel`() {
        val expected = expectedConvertedRoomMovieModel()
        val movie = setMovieModel()

        roomStorage.deleteMovie(movie)
        verify(movieDao).deleteMovies(expected)
    }

    @Test
    fun `test when call getFavoriteMovieList expect list of expectedConvertedMovieModel`() {
        val expected = listOf(expectedConvertedMovieModel())

        whenever(movieDao.getFavoriteMovieList()).thenReturn(listOf(setRoomModel()))

        val result = roomStorage.getFavoriteMovieList()

        assertEquals(expected, result)
    }

    private fun expectedConvertedRoomMovieModel() = RoomMovieModel(
        "teste123",
        "teste",
        "descricao",
        "tipo",
        0.0,
        0.0,
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList()
    )

    private fun expectedConvertedMovieModel() = Movie(
        "teste",
        "descricao",
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

    private fun setRoomModel() = RoomMovieModel(
        "teste123",
        "teste",
        "descricao",
        "tipo",
        0.0,
        0.0,
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList()
    )

    private fun setMovieModel() = Movie(
        "teste",
        "descricao",
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