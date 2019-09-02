package com.mayarafernandes.movieplayer.movieList.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mayarafernandes.movieplayer.R
import com.mayarafernandes.movieplayer.movieList.*
import com.mayarafernandes.movieplayer.movieList.MovieRepository
import com.mayarafernandes.movieplayer.movieList.storage.MemoryRepository
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListActivity : AppCompatActivity(), MovieListView, MovieViewModelClickListener {

    private lateinit var controller: MovieListController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        val memoryRepository = MemoryRepository()
        val movieRepository =
            MovieRepository(memoryRepository)
        val presenter = MovieListPresenter()
        controller = MovieListController(movieRepository, presenter, this)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        movieListRecyclerView.layoutManager = layoutManager

        val rvAdapter = MovieListRecyclerViewAdapter(this, this)
        movieListRecyclerView.adapter = rvAdapter

        controller.onViewCreated()
    }

    override fun setViewModel(viewModels: List<MovieViewModel>) {
        val rvAdapter = movieListRecyclerView.adapter as MovieListRecyclerViewAdapter
        rvAdapter.movieList = viewModels
    }

    override fun onClick(movie: MovieViewModel) {
        controller.onSelectMovie(movie)
    }
}
