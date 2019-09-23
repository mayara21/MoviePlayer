package com.mayarafernandes.movieplayer.movieList.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.ToggleButton
import androidx.recyclerview.widget.LinearLayoutManager
import com.mayarafernandes.movieplayer.R
import com.mayarafernandes.movieplayer.movieList.favorites.FavoritesRepository
import com.mayarafernandes.movieplayer.movieList.favorites.repository.MovieDao
import com.mayarafernandes.movieplayer.movieList.favorites.repository.MovieRoomDatabase
import com.mayarafernandes.movieplayer.movieList.favorites.repository.RoomStorage
import com.mayarafernandes.movieplayer.movieList.MovieListController
import com.mayarafernandes.movieplayer.movieList.repository.MovieRepository
import com.mayarafernandes.movieplayer.movieList.repository.storage.MemoryRepository
import kotlinx.android.synthetic.main.activity_movie_list.*
import kotlinx.android.synthetic.main.fragment_movie_list.view.*

class MovieListFragment : Fragment(), MovieListView, MovieViewModelClickListener,
    FavoriteButtonCheckListener {

    private lateinit var fragmentContext: Context
    private lateinit var controller: MovieListController
    private lateinit var presenter: MovieListPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var movieDao: MovieDao

    override fun onAttach(context: Context) {
        fragmentContext = context
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        progressBar = view.movieListLoadingProgressBar

        movieDao = MovieRoomDatabase.getInstance().movieDao()

        val roomStorage = RoomStorage(movieDao)
        val favoritesRepository =
            FavoritesRepository(roomStorage)
        val memoryRepository = MemoryRepository()
        val movieRepository =
            MovieRepository(memoryRepository)

        presenter = MovieListPresenter()
        controller = MovieListController(movieRepository, presenter, this, favoritesRepository)

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        view.movieListRecyclerView.layoutManager = layoutManager

        val rvAdapter = MovieListRecyclerViewAdapter(fragmentContext, this, this)
        view.movieListRecyclerView.adapter = rvAdapter

        controller.onViewCreated()
        return view
    }

    override fun onStart() {
        super.onStart()
        noConnectionText.visibility = View.INVISIBLE
    }

    override fun setViewModel(viewModels: List<MovieViewModel>) {
        activity?.runOnUiThread {
            val rvAdapter = movieListRecyclerView.adapter as MovieListRecyclerViewAdapter
            rvAdapter.updateMovieList(viewModels)
        }
    }

    override fun onClick(movie: MovieViewModel) {
        controller.onSelectMovie(movie)
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun showMovieList() {
        movieListRecyclerView.visibility = View.VISIBLE
        noConnectionText.visibility = View.INVISIBLE
    }

    override fun hideMovieList() {
        movieListRecyclerView.visibility = View.INVISIBLE
        noConnectionText.visibility = View.VISIBLE
    }

    override fun onCheckedChange(movie: MovieViewModel) {
        controller.addToFavorites(movie)
    }

    override fun onUncheckedChange(movie: MovieViewModel) {
        controller.removeFromFavorites(movie)
    }
}