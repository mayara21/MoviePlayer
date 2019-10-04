package com.mayarafernandes.movieplayer.navigationBar.movies.view

import com.mayarafernandes.movieplayer.navigationBar.movies.repository.Image
import com.mayarafernandes.movieplayer.navigationBar.movies.repository.Movie
import org.junit.Assert.*
import org.junit.Test

class MovieListPresenterImplTest {

    private val presenter = MovieListPresenterImpl()
    private val movie = setMovie()
    private val expected = setResult()

    @Test
    fun `test when call convertModel expect converted to ViewModel`() {
        val result = presenter.convertModel(movie)
        assertEquals(expected, result)
    }

    private fun setMovie() = Movie(
        "filme teste",
        "descricao teste",
        "tipo",
        2434874387.0,
        8574975893.0,
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        listOf(Image("cover", "oioi", 3, 3, "image")),
        emptyList(),
        "teste123",
        false
    )

    private fun setResult() = MovieViewModel("filme teste", "descricao teste", "oioi", "teste123", false, 0)
}