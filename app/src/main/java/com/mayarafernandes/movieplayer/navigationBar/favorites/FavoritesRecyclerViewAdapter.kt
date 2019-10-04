package com.mayarafernandes.movieplayer.navigationBar.favorites

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mayarafernandes.movieplayer.R
import com.mayarafernandes.movieplayer.navigationBar.keepWatchingList.WatchClickListener
import com.mayarafernandes.movieplayer.navigationBar.movies.view.MovieViewModel
import com.mayarafernandes.movieplayer.navigationBar.movies.view.MovieViewModelClickListener
import kotlinx.android.synthetic.main.item_favorite.view.*
import kotlinx.android.synthetic.main.item_movie.view.movieDescriptionTextView
import kotlinx.android.synthetic.main.item_movie.view.movieIconImageView
import kotlinx.android.synthetic.main.item_movie.view.movieTitleTextView

class FavoritesRecyclerViewAdapter(private val context: Context, private val clickListener: MovieViewModelClickListener, private val removeListener: RemoveButtonListener, private val watchClickListener: WatchClickListener): RecyclerView.Adapter<FavoritesRecyclerViewAdapter.MyViewHolder>() {

    private var movieList = mutableListOf<MovieViewModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_favorite, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        val movie = movieList[position]

        holder.title.text = movie.title
        holder.description.text = movie.description

        val url = movie.urlImage
        Glide.with(context)
            .load(url)
            .circleCrop()
            .into(holder.imageView)

        holder.itemView.setOnClickListener {
            clickListener.onClick(movie)
        }

        holder.playIcon.setOnClickListener {
            watchClickListener.onClickPlay(movie)
        }

        holder.progressBar.progress = movie.progress

        val removeButton = holder.itemView.removeButton

        removeButton.setOnClickListener {
            removeListener.onClickRemove(movie)
            movieList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, itemCount)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun updateMovieList(movies: List<MovieViewModel>)  {
        movieList.addAll(movies)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.movieTitleTextView
        val description: TextView = itemView.movieDescriptionTextView
        val imageView: ImageView = itemView.movieIconImageView
        val playIcon: ImageView = itemView.playButton
        val progressBar: ProgressBar = itemView.movieProgressBar
    }
}