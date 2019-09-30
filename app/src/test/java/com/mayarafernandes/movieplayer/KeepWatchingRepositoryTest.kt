package com.mayarafernandes.movieplayer

import com.mayarafernandes.movieplayer.movieList.view.MovieViewModel
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert.*
import org.junit.Test

class KeepWatchingRepositoryTest {

    private val localStorage = mock<LocalProgressStorage>()
    private val repository = KeepWatchingRepository(localStorage)
    private val movie = setMovieViewModel()

    @Test
    fun `test when call saveProgress expect call local storage saveProgress`() {
        val progress = 36000.0
        val percentage = .5
        repository.saveProgress(movie, progress, percentage)
        verify(localStorage).saveProgress(movie, progress, percentage)
    }

    @Test
    fun `test when call getProgress expect call local storage getProgressPercentage`() {
        repository.getProgress(movie)
        verify(localStorage).getProgressPercentage(movie)
    }

    @Test
    fun `test when call getProgress expect return correct percentage`() {
        val expected = .4
        val allowedError = .0000001
        whenever(localStorage.getProgressPercentage(movie)).thenReturn(expected)
        val result = repository.getProgress(movie)

        assertEquals(expected, result, allowedError)
    }

    @Test
    fun `test when call returnKeepWatchingList expect call local storage getMoviesProgressList`() {
        repository.returnKeepWatchingList()
        verify(localStorage).getMoviesProgressList()
    }

    @Test
    fun `test when call returnKeepWatchingList expect return correct list`() {

    }


    private fun setMovieViewModel() = MovieViewModel(
        "title",
        "description",
        "url",
        "id",
        false
    )

}