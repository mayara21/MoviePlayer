package com.mayarafernandes.movieplayer.movieList.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mayarafernandes.movieplayer.R
import kotlinx.android.synthetic.main.adapter_movie_item.view.*

class MovieListRecyclerViewAdapter(private val context: Context, private val movieList: List<MovieModel>): RecyclerView.Adapter<MovieListRecyclerViewAdapter.MyViewHolder>() {

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
        holder.title.text = movieList[position].title
        holder.description.text = movieList[position].description
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.movieTitleTextView
        val description: TextView = itemView.movieDescriptionTextView
    }
}