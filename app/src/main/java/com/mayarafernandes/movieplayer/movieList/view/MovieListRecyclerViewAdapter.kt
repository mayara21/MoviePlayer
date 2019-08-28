package com.mayarafernandes.movieplayer.movieList.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mayarafernandes.movieplayer.MovieDb
import com.mayarafernandes.movieplayer.R
import kotlinx.android.synthetic.main.adapter_movie_item.view.*

class MovieListRecyclerViewAdapter(private val context: Context, private val db: MovieDb): RecyclerView.Adapter<MovieListRecyclerViewAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_movie_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return db.getSize()
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        holder.title.text = db.getMovieTitle(position)
        holder.description.text = db.getMovieDescription(position)
        val url = db.getMovieImageUrl(position)

        //holder.title.text = movieList[position].title
        //holder.description.text = movieList[position].description
        Glide.with(context)
             .load(url)
             .into(holder.imageView)
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.movieTitleTextView
        val description: TextView = itemView.movieDescriptionTextView
        val imageView: ImageView = itemView.movieIconImageView
    }
}