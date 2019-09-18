package com.mayarafernandes.movieplayer.movieList.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mayarafernandes.movieplayer.R
import kotlinx.android.synthetic.main.adapter_movie_item.view.*

class MovieListRecyclerViewAdapter(private val context: Context, private val clickListener: MovieViewModelClickListener, private val checkListener: FavoriteButtonCheckListener): RecyclerView.Adapter<MovieListRecyclerViewAdapter.MyViewHolder>() {

    private var movieList = mutableListOf<MovieViewModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_movie_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
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

        val favoriteButton = holder.itemView.button_favorite
        favoriteButton.isChecked = movie.isFavorite

        favoriteButton.setOnClickListener {
            if(favoriteButton.isChecked) checkListener.onCheckedChange(movie, favoriteButton)
            else checkListener.onUncheckedChange(movie, favoriteButton)
        }
    }

    fun updateMovieList(movies: List<MovieViewModel>)  {
        movieList.addAll(movies)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.movieTitleTextView
        val description: TextView = itemView.movieDescriptionTextView
        val imageView: ImageView = itemView.movieIconImageView
    }
}