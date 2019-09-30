package com.mayarafernandes.movieplayer.movieList.repository

import com.mayarafernandes.movieplayer.movieList.repository.service.MovieCallbacks
import com.mayarafernandes.movieplayer.movieList.repository.service.MovieService
import com.mayarafernandes.movieplayer.movieList.repository.service.MovieServiceAccess
import com.mayarafernandes.movieplayer.movieList.repository.storage.LocalMovieStorage
import com.nhaarman.mockito_kotlin.*
import org.junit.Assert.*
import org.junit.Test

class MovieRepositoryTest {
    private val localStorage = mock<LocalMovieStorage>()
    private val movieCallbacks = mock<MovieCallbacks>()
    private val movieService = mock<MovieService>()
    private val movieRepository = MovieRepositoryImpl(localStorage, movieService)

    @Test
    fun `test when call onMovieSelected expect call getMovieById`() {
        val movieId = "test"
        movieRepository.onMovieSelected(movieId)
        verify(localStorage).getMovieById(movieId)
    }

    @Test
    fun `test when call onMovieSelected expect expected movie` () {
        val movieId = "teste123"
        whenever(localStorage.getMovieById(movieId)).thenReturn(setMovie())

        val result = movieRepository.onMovieSelected(movieId)
        val expected = setMovie()

        assertEquals(expected, result)
    }

    @Test
    fun `test when call returnMovieList expect returnMovieList from movieService when success or fail`() {
        setCallbackSuccess()
        movieRepository.returnMovieList(movieCallbacks)
        verify(movieService, times(1)).returnMovieList(any())
    }

    @Test
    fun `test when call returnMovieList expect saveMovieList when success`() {
        setCallbackSuccess()
        movieRepository.returnMovieList(movieCallbacks)
        verify(localStorage).saveMovieList(any())
    }

    @Test
    fun `test when call returnMovieList expect onSuccess from movieCallback when success`() {
        setCallbackSuccess()
        movieRepository.returnMovieList(movieCallbacks)
        verify(movieCallbacks).onSuccess(any())
    }

    @Test
    fun `test when call returnMovieList expect onError from movieCallback when fail`() {
        setCallbackError()
        movieRepository.returnMovieList(movieCallbacks)
        verify(movieCallbacks).onError()
    }

    private fun setCallbackSuccess() {
        val movies = setMovieList()

        doAnswer {
            val callback = it.arguments[0] as MovieCallbacks
            callback.onSuccess(movies)
            return@doAnswer null
        }.whenever(movieService).returnMovieList(any())
    }

    private fun setCallbackError() {
        doAnswer {
            val callback = it.arguments[0] as MovieCallbacks
            callback.onError()
            return@doAnswer null
        }.whenever(movieService).returnMovieList(any())
    }

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

    private fun setMovieList() = listOf(setMovie())
}