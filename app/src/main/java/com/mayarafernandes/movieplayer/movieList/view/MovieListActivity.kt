package com.mayarafernandes.movieplayer.movieList.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.mayarafernandes.movieplayer.R
import com.mayarafernandes.movieplayer.movieList.*
import com.mayarafernandes.movieplayer.movieList.repository.MovieRepository
import com.mayarafernandes.movieplayer.movieList.repository.storage.MemoryRepository
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListActivity : AppCompatActivity(),
    MovieListView, MovieViewModelClickListener {

    private lateinit var controller: MovieListController
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        progressBar = findViewById(R.id.movieListLoadingProgressBar)

        val memoryRepository = MemoryRepository()
        val movieRepository =
            MovieRepository(memoryRepository)
        val presenter = MovieListPresenter()
        controller = MovieListController(movieRepository, presenter, this)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        movieListRecyclerView.layoutManager = layoutManager

        val rvAdapter = MovieListRecyclerViewAdapter(this, this, progressBar)
        movieListRecyclerView.adapter = rvAdapter

        controller.onViewCreated()
    }

    override fun setViewModel(viewModels: List<MovieViewModel>) {
        val rvAdapter = movieListRecyclerView.adapter as MovieListRecyclerViewAdapter
        rvAdapter.updateMovieList(viewModels)
    }

    override fun onClick(movie: MovieViewModel) {
        controller.onSelectMovie(movie)
    }

    override fun showProgressBar(show: Boolean) {
        if(show) progressBar.visibility = View.VISIBLE
        else progressBar.visibility = View.GONE
    }
}
