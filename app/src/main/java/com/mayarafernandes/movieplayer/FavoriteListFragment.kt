package com.mayarafernandes.movieplayer

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.ToggleButton
import androidx.recyclerview.widget.LinearLayoutManager
import com.mayarafernandes.movieplayer.favorites.FavoritesRepository
import com.mayarafernandes.movieplayer.favorites.repository.MovieDao
import com.mayarafernandes.movieplayer.favorites.repository.MovieRoomDatabase
import com.mayarafernandes.movieplayer.favorites.repository.RoomStorage
import com.mayarafernandes.movieplayer.movieList.MovieListController
import com.mayarafernandes.movieplayer.movieList.repository.MovieRepository
import com.mayarafernandes.movieplayer.movieList.repository.storage.MemoryRepository
import com.mayarafernandes.movieplayer.movieList.view.*
import kotlinx.android.synthetic.main.activity_movie_list.*
import kotlinx.android.synthetic.main.activity_movie_list.noConnectionText
import kotlinx.android.synthetic.main.fragment_favorite_list.*
import kotlinx.android.synthetic.main.fragment_favorite_list.view.*
import kotlinx.android.synthetic.main.fragment_movie_list.view.*

class FavoriteListFragment : Fragment(), MovieListView, MovieViewModelClickListener,
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
        val view = inflater.inflate(R.layout.fragment_favorite_list, container, false)

        progressBar = view.favoriteListLoadingProgressBar

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
        view.favoriteListRecyclerView.layoutManager = layoutManager

        val rvAdapter = MovieListRecyclerViewAdapter(fragmentContext, this, this)
        view.favoriteListRecyclerView.adapter = rvAdapter

        controller.onViewCreated()
        return view

    }

    override fun onStart() {
        super.onStart()
        noConnectionText.visibility = View.INVISIBLE
    }


    override fun setViewModel(viewModels: List<MovieViewModel>) {
        activity?.runOnUiThread {
            val rvAdapter = favoriteListRecyclerView.adapter as MovieListRecyclerViewAdapter
            rvAdapter.updateMovieList(controller.getFavorites(viewModels))
        }
    }

    override fun showProgressBar(show: Boolean) {
        if(show) progressBar.visibility = View.VISIBLE
        else progressBar.visibility = View.GONE
    }

    override fun showMovieList(show: Boolean) {
        if(show) {
            favoriteListRecyclerView.visibility = View.VISIBLE
            noConnectionText.visibility = View.INVISIBLE
        }

        else {
            favoriteListRecyclerView.visibility = View.INVISIBLE
            noConnectionText.visibility = View.VISIBLE
        }
    }

    override fun onClick(movie: MovieViewModel) {
        controller.onSelectMovie(movie)
    }

    override fun onCheckedChange(movie: MovieViewModel, favoriteButton: ToggleButton) {
        controller.addToFavorites(movie)
        presenter.setFavoriteButton(favoriteButton)
    }

    override fun onUncheckedChange(movie: MovieViewModel, favoriteButton: ToggleButton) {
        controller.removeFromFavorites(movie)
        presenter.setFavoriteButton(favoriteButton)
    }
}
