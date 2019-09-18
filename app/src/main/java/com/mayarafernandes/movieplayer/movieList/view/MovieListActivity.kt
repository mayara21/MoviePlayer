package com.mayarafernandes.movieplayer.movieList.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.ToggleButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.mayarafernandes.movieplayer.*
import com.mayarafernandes.movieplayer.favorites.FavoritesRepository
import com.mayarafernandes.movieplayer.movieList.*
import com.mayarafernandes.movieplayer.movieList.repository.MovieRepository
import com.mayarafernandes.movieplayer.movieList.repository.storage.MemoryRepository
import com.mayarafernandes.movieplayer.favorites.repository.MovieDao
import com.mayarafernandes.movieplayer.favorites.repository.MovieRoomDatabase
import com.mayarafernandes.movieplayer.favorites.repository.RoomStorage
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListActivity : AppCompatActivity(),
    MovieListView, MovieViewModelClickListener, FavoriteButtonCheckListener {

    private lateinit var controller: MovieListController
    private lateinit var presenter: MovieListPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var movieDao: MovieDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        noConnectionText.visibility = View.INVISIBLE
        progressBar = findViewById(R.id.movieListLoadingProgressBar)

        val database = Room.databaseBuilder(this, MovieRoomDatabase::class.java, "favorite-movies-database").build() // mudar isso pra ser assincrono depois
        movieDao = database.movieDao()

        val roomStorage = RoomStorage(movieDao)
        val favoritesRepository =
            FavoritesRepository(roomStorage)
        val memoryRepository = MemoryRepository()
        val movieRepository =
            MovieRepository(memoryRepository)
        presenter = MovieListPresenter()
        controller = MovieListController(movieRepository, presenter, this, favoritesRepository)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        movieListRecyclerView.layoutManager = layoutManager

        val rvAdapter = MovieListRecyclerViewAdapter(this, this, this)
        movieListRecyclerView.adapter = rvAdapter

        controller.onViewCreated()
    }

    override fun setViewModel(viewModels: List<MovieViewModel>) {
        runOnUiThread {
            val rvAdapter = movieListRecyclerView.adapter as MovieListRecyclerViewAdapter
            rvAdapter.updateMovieList(viewModels)
        }
    }

    override fun onClick(movie: MovieViewModel) {
        controller.onSelectMovie(movie)
    }

    override fun showProgressBar(show: Boolean) {
        if(show) progressBar.visibility = View.VISIBLE
        else progressBar.visibility = View.GONE
    }

    override fun showMovieList(show: Boolean) {
        if(show) {
            movieListRecyclerView.visibility = View.VISIBLE
            noConnectionText.visibility = View.INVISIBLE
        }

        else {
            movieListRecyclerView.visibility = View.INVISIBLE
            noConnectionText.visibility = View.VISIBLE
        }
    }

    override fun onCheckedChange(
        movie: MovieViewModel,
        favoriteButton: ToggleButton
    ) {
        controller.addToFavorites(movie)
        presenter.setFavoriteButton(favoriteButton)

    }

    override fun onUncheckedChange(
        movie: MovieViewModel,
        favoriteButton: ToggleButton
    ) {
        controller.removeFromFavorites(movie)
        presenter.setFavoriteButton(favoriteButton)
    }
}
