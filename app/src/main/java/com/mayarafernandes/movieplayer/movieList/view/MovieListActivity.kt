package com.mayarafernandes.movieplayer.movieList.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mayarafernandes.movieplayer.R
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        movieListRecyclerView.layoutManager = layoutManager

        val movieList = Supplier.movies
        val rvAdapter = MovieListRecyclerViewAdapter(this, movieList)
        movieListRecyclerView.adapter = rvAdapter
    }
}
