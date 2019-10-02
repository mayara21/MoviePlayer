package com.mayarafernandes.movieplayer.movieList.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mayarafernandes.movieplayer.R
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieListRecyclerViewAdapter(private val context: Context, private val clickListener: MovieViewModelClickListener, private val checkListener: FavoriteButtonCheckListener): RecyclerView.Adapter<MovieListRecyclerViewAdapter.MyViewHolder>() {

    private var movieList = mutableListOf<MovieViewModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
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
        val progressBar = holder.itemView.movieProgressBar

        favoriteButton.isChecked = movie.isFavorite

        favoriteButton.setOnClickListener {
            if(favoriteButton.isChecked) checkListener.onCheckedChange(movie)
            else checkListener.onUncheckedChange(movie)
            setFavoriteButton(favoriteButton)
        }

        progressBar.progress = movie.progress
    }

    fun updateMovieList(movies: List<MovieViewModel>)  {
        movieList.addAll(movies)
        notifyDataSetChanged()
    }

    private fun setFavoriteButton(favoriteButton: ToggleButton) {
        favoriteButton.startAnimation(setButtonFavoriteAnimation())
    }

    private fun setButtonFavoriteAnimation(): ScaleAnimation {
        val scaleAnimation = ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF, 0.7f, Animation.RELATIVE_TO_SELF, 0.7f)
        scaleAnimation.duration = 500
        val bounceInterpolator = BounceInterpolator()
        scaleAnimation.interpolator = bounceInterpolator

        return scaleAnimation
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.movieTitleTextView
        val description: TextView = itemView.movieDescriptionTextView
        val imageView: ImageView = itemView.movieIconImageView
    }
}