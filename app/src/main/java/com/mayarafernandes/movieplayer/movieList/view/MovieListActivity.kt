package com.mayarafernandes.movieplayer.movieList.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mayarafernandes.movieplayer.movieList.ItemMovie
import com.mayarafernandes.movieplayer.R
import com.mayarafernandes.movieplayer.movieList.MovieListController
import com.mayarafernandes.movieplayer.movieList.MovieListPresenter
import com.mayarafernandes.movieplayer.movieList.MovieListView
import com.mayarafernandes.movieplayer.movieList.service.TempService
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListActivity : AppCompatActivity(), MovieListView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        val itemMovie = ItemMovie()
        val presenter = MovieListPresenter()
        val controller = MovieListController(itemMovie, presenter, this)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        movieListRecyclerView.layoutManager = layoutManager

        val rvAdapter = MovieListRecyclerViewAdapter(this)
        movieListRecyclerView.adapter = rvAdapter

        controller.onViewCreated()
    }

    override fun setViewModel(viewModels: List<MovieViewModel>) {
        val rvAdapter = movieListRecyclerView.adapter as MovieListRecyclerViewAdapter
        rvAdapter.movieList = viewModels
    }
}
