package com.mayarafernandes.movieplayer

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mayarafernandes.movieplayer.movieList.MovieListController
import com.mayarafernandes.movieplayer.movieList.favorites.FavoritesRecyclerViewAdapter
import com.mayarafernandes.movieplayer.movieList.favorites.FavoritesRepositoryImpl
import com.mayarafernandes.movieplayer.movieList.favorites.repository.MovieDao
import com.mayarafernandes.movieplayer.movieList.favorites.repository.MovieRoomDatabase
import com.mayarafernandes.movieplayer.movieList.favorites.repository.RoomStorage
import com.mayarafernandes.movieplayer.movieList.repository.MovieRepositoryImpl
import com.mayarafernandes.movieplayer.movieList.repository.service.MovieServiceIMPL
import com.mayarafernandes.movieplayer.movieList.repository.storage.MemoryRepository
import com.mayarafernandes.movieplayer.movieList.view.*
import kotlinx.android.synthetic.main.activity_movie_list.*
import kotlinx.android.synthetic.main.fragment_favorite_list.*
import kotlinx.android.synthetic.main.fragment_favorite_list.noConnectionText
import kotlinx.android.synthetic.main.fragment_favorite_list.view.*
import kotlinx.android.synthetic.main.fragment_watching_list.*
import kotlinx.android.synthetic.main.fragment_watching_list.view.*

class KeepWatchingListFragment: Fragment(), MovieListView, MovieViewModelClickListener, WatchClickListener {

    private lateinit var fragmentContext: Context
    private lateinit var controller: MovieListController
    private lateinit var presenter: MovieListPresenterImpl
    private lateinit var progressBar: ProgressBar
    private lateinit var movieDao: MovieDao
    private lateinit var progressDao: ProgressDao

    override fun onAttach(context: Context) {
        fragmentContext = context
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_watching_list, container, false)

        progressBar = view.watchingListLoadingProgressBar

        movieDao = MovieRoomDatabase.getInstance().movieDao()
        progressDao = MovieRoomDatabase.getInstance().progressDao()

        val roomStorage = RoomStorage(movieDao)
        val favoritesRepository =
            FavoritesRepositoryImpl(roomStorage)
        val memoryRepository = MemoryRepository()
        val movieService = MovieServiceIMPL()
        val movieRepository =
            MovieRepositoryImpl(memoryRepository, movieService)

        val progressStorage = RoomProgressStorage(progressDao)

        val keepWatchingRepository = KeepWatchingRepositoryImpl(progressStorage, memoryRepository)

        presenter = MovieListPresenterImpl()
        controller = MovieListController(movieRepository, presenter, this, favoritesRepository, keepWatchingRepository)

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        view.watchingListRecyclerView.layoutManager = layoutManager

        val rvAdapter = WatchingListRecyclerViewAdapter(fragmentContext, this, this)
        view.watchingListRecyclerView.adapter = rvAdapter

        controller.onViewCreated()
        return view
    }

    override fun setViewModel(viewModels: List<MovieViewModel>) {
        activity?.runOnUiThread {
            val rvAdapter = watchingListRecyclerView.adapter as WatchingListRecyclerViewAdapter
            rvAdapter.updateMovieList(controller.getWatching(viewModels))
        }
    }

    override fun onClick(movie: MovieViewModel) {
        controller.onSelectMovie(movie)
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        activity?.runOnUiThread {
            progressBar.visibility = View.GONE
        }
    }

    override fun showMovieList() {
        watchingListRecyclerView.visibility = View.VISIBLE
        noConnectionText.visibility = View.INVISIBLE
    }

    override fun hideMovieList() {
        watchingListRecyclerView.visibility = View.INVISIBLE
        noConnectionText.visibility = View.VISIBLE
    }

    override fun onClickPlay(movie: MovieViewModel) {
        //temporary behavior
        val watched = 1000000.0
        controller.saveProgress(movie, watched)
    }
}