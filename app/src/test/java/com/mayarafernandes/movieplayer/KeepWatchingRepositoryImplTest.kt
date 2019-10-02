package com.mayarafernandes.movieplayer

import com.mayarafernandes.movieplayer.movieList.repository.Content
import com.mayarafernandes.movieplayer.movieList.repository.Movie
import com.mayarafernandes.movieplayer.movieList.repository.storage.LocalMovieStorage
import com.mayarafernandes.movieplayer.movieList.view.MovieViewModel
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert.*
import org.junit.Test

class KeepWatchingRepositoryImplTest {

    private val memoryRepository = mock<LocalMovieStorage>()
    private val localStorage = mock<LocalProgressStorage>()
    private val repository = KeepWatchingRepositoryImpl(localStorage, memoryRepository)
    private val movie = setMovieViewModel()

    @Test
    fun `test when call saveProgress expect call local storage saveProgress`() {
        val watched = 4000.0
        val expectedProgress = 50

        whenever(memoryRepository.returnMovieList()).thenReturn(setMovieList())
        repository.saveProgress(movie, watched)

        verify(localStorage).saveProgress(ProgressModel(movie.id, expectedProgress))
    }

    @Test
    fun `test when call getProgress expect call local storage getProgressPercentage`() {
        repository.getProgress(movie.id)
        verify(localStorage).getProgressPercentage(movie.id)
    }

    @Test
    fun `test when call getProgress expect return correct percentage`() {
        val expected = 40
        whenever(localStorage.getProgressPercentage(movie.id)).thenReturn(expected)
        val result = repository.getProgress(movie.id)

        assertEquals(expected, result)
    }

    @Test
    fun `test when call returnKeepWatchingList expect call local storage getMoviesProgressList`() {
        repository.returnKeepWatchingList()
        verify(localStorage).getMoviesProgressList()
    }

    /*@Test
    fun `test when call returnKeepWatchingList expect return correct list`() {

    }*/

    private fun setMovieViewModel() = MovieViewModel(
        "title",
        "description",
        "url",
        "id",
        false,
        0
    )

    private fun setMovieList() = listOf(
        Movie(
            "title",
            "description",
            "type",
            0.0,
            0.0,
            emptyList(),
            listOf(Content("", "", 10, 10, "", 8000.0, false, "vid101")),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            "id",
            false
        ), Movie(
            "title2",
            "description2",
            "type",
            0.0,
            0.0,
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            "id2",
            false
        )
    )
}