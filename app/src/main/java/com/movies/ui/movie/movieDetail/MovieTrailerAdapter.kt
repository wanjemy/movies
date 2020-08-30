package com.movies.ui.movie.movieDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.movies.R
import com.movies.base.BaseRecyclerViewAdapter
import com.movies.model.common.video.VideoModelCommon

class MovieTrailerAdapter : BaseRecyclerViewAdapter<VideoModelCommon>() {
    override fun generateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        TrailerItem(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rv_item_genre, parent, false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as TrailerItem
        val trailer = recyclerviewItem[position]

        holder.bind(trailer)
    }

    private inner class TrailerItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvMovieTrailerName: TextView = itemView.findViewById(R.id.tvGenreName)

        fun bind(trailer: VideoModelCommon) {
            tvMovieTrailerName.text = trailer.name
        }
    }
}