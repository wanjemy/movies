package com.movies.ui.movie.moviesByGenre

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movies.R
import com.movies.base.BaseRecyclerViewAdapter
import com.movies.model.common.movie.MovieModelCommon

class MoviesAdapter : BaseRecyclerViewAdapter<MovieModelCommon>() {
    override fun generateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MovieItem(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_item_movie, parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }

    private inner class MovieItem(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}