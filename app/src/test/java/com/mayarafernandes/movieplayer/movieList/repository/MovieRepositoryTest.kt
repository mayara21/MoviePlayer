package com.mayarafernandes.movieplayer.movieList.repository

import com.mayarafernandes.movieplayer.movieList.repository.service.MovieCallbacks
import com.mayarafernandes.movieplayer.movieList.repository.storage.LocalMovieStorage
import com.nhaarman.mockito_kotlin.doAnswer
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert.*
import org.junit.Test

class MovieRepositoryTest {
    private val localStorage = mock<LocalMovieStorage>()
    private val movieCallbacks = mock<MovieCallbacks>()
    private val movieRepository = MovieRepositoryImpl(localStorage)

    @Test
    fun `test when call onMovieSelected expect expected movie` () {
        val movieId = "teste123"
        whenever(localStorage.getMovieById(movieId)).thenReturn(setMovie())

        val result = movieRepository.onMovieSelected(movieId)
        val expected = setMovie()

        assertEquals(expected, result)
    }

    /*@Test
    fun `test when call returnMovieList expect movie list`() {
        doAnswer { invocationOnMock -> invocationOnMock.arguments[0]. }
    }*/

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