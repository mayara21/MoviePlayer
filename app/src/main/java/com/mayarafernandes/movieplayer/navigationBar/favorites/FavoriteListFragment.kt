package com.mayarafernandes.movieplayer.navigationBar.favorites

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.mayarafernandes.movieplayer.*
import com.mayarafernandes.movieplayer.navigationBar.favorites.repository.MovieDao
import com.mayarafernandes.movieplayer.navigationBar.favorites.repository.MovieRoomDatabase
import com.mayarafernandes.movieplayer.navigationBar.favorites.repository.RoomStorage
import com.mayarafernandes.movieplayer.navigationBar.keepWatchingList.repository.KeepWatchingRepositoryImpl
import com.mayarafernandes.movieplayer.navigationBar.keepWatchingList.repository.ProgressDao
import com.mayarafernandes.movieplayer.navigationBar.keepWatchingList.repository.RoomProgressStorage
import com.mayarafernandes.movieplayer.navigationBar.keepWatchingList.WatchClickListener
import com.mayarafernandes.movieplayer.navigationBar.movies.repository.MovieRepositoryImpl
import com.mayarafernandes.movieplayer.navigationBar.movies.repository.service.MovieServiceIMPL
import com.mayarafernandes.movieplayer.navigationBar.movies.repository.storage.MemoryRepository
import com.mayarafernandes.movieplayer.navigationBar.movies.view.*
import kotlinx.android.synthetic.main.activity_movie_list.noConnectionText
import kotlinx.android.synthetic.main.fragment_favorite_list.*
import kotlinx.android.synthetic.main.fragment_favorite_list.view.*

class FavoriteListFragment : Fragment(), MovieListView, MovieViewModelClickListener,
    RemoveButtonListener,
    WatchClickListener {

    private lateinit var fragmentContext: Context
    private lateinit var controller: FavoriteListController
    private lateinit var presenter: MovieListPresenter
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
        val view = inflater.inflate(R.layout.fragment_favorite_list, container, false)

        progressBar = view.favoriteListLoadingProgressBar

        movieDao = MovieRoomDatabase.getInstance().movieDao()
        progressDao = MovieRoomDatabase.getInstance().progressDao()

        val roomStorage = RoomStorage(movieDao)
        val favoritesRepository =
            FavoritesRepositoryImpl(roomStorage)
        val memoryRepository = MemoryRepository
        val movieService = MovieServiceIMPL()
        val movieRepository =
            MovieRepositoryImpl(memoryRepository, movieService)

        val progressStorage =
            RoomProgressStorage(
                progressDao
            )

        val keepWatchingRepository =
            KeepWatchingRepositoryImpl(
                progressStorage,
                memoryRepository
            )

        presenter = MovieListPresenterImpl()
        controller = FavoriteListController(
            movieRepository,
            presenter,
            this,
            favoritesRepository,
            keepWatchingRepository,
            memoryRepository
        )

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        view.favoriteListRecyclerView.layoutManager = layoutManager

        val rvAdapter = FavoritesRecyclerViewAdapter(fragmentContext, this, this, this)
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
            val rvAdapter = favoriteListRecyclerView.adapter as FavoritesRecyclerViewAdapter
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
        activity?.runOnUiThread {
            progressBar.visibility = View.GONE
        }
    }

    override fun showMovieList() {
        favoriteListRecyclerView.visibility = View.VISIBLE
        noConnectionText.visibility = View.INVISIBLE
    }

    override fun hideMovieList() {
        favoriteListRecyclerView.visibility = View.INVISIBLE
        noConnectionText.visibility = View.VISIBLE
    }

    override fun onClickRemove(movie: MovieViewModel) {
        controller.removeFromFavorites(movie)
    }

    override fun onClickPlay(movie: MovieViewModel) {
        //temporary behavior
        val watched = 1000000.0
        controller.saveProgress(movie, watched)
    }
}
