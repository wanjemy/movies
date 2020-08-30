package com.movies.ui.movie.movieDetail

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.movies.R
import com.movies.base.BaseRecyclerViewAdapter
import com.movies.model.common.video.VideoModelCommon
import java.lang.Exception

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

    override fun addNewData(newData: List<VideoModelCommon>) {
        val youTubeTrailer = newData.filter { it.site == "YouTube" && it.type == "Trailer" }
        super.addNewData(youTubeTrailer)
    }

    private inner class TrailerItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvMovieTrailerName: TextView = itemView.findViewById(R.id.tvGenreName)

        fun bind(trailer: VideoModelCommon) {
            tvMovieTrailerName.text = trailer.name

            itemView.setOnClickListener {
                val intentToYoutube = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=${trailer.key}")
                )

                try {
                    itemView.context.startActivity(intentToYoutube)
                } catch (e: Exception) {
                }
            }
        }
    }
}