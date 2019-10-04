package com.mayarafernandes.movieplayer.navigationBar.keepWatchingList

import android.annotation.SuppressLint
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
import com.mayarafernandes.movieplayer.navigationBar.movies.view.MovieViewModel
import com.mayarafernandes.movieplayer.navigationBar.movies.view.MovieViewModelClickListener
import kotlinx.android.synthetic.main.item_watching.view.*

class WatchingListRecyclerViewAdapter(private val context: Context, private val clickListener: MovieViewModelClickListener, private val watchClickListener: WatchClickListener): RecyclerView.Adapter<WatchingListRecyclerViewAdapter.MyViewHolder>() {

    private var movieList = mutableListOf<MovieViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_watching, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = movieList[position]

        holder.title.text = movie.title

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
        holder.progressText.text = "${movie.progress}%"

    }

    fun updateMovieList(movies: List<MovieViewModel>)  {
        movieList.addAll(movies)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.movieTitleTextView
        val imageView: ImageView = itemView.movieIconImageView
        val progressBar: ProgressBar = itemView.movieProgressBar
        val progressText: TextView = itemView.percentageTextView
        val playIcon: ImageView = itemView.playButton
    }
}