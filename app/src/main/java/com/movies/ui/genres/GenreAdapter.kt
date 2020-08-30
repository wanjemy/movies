package com.movies.ui.genres

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.movies.R
import com.movies.base.BaseRecyclerViewAdapter
import com.movies.model.common.genre.GenreModelCommon
import com.movies.ui.movie.moviesByGenre.MoviesByGenre

class GenreAdapter : BaseRecyclerViewAdapter<GenreModelCommon>() {
    override fun generateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        GenreItem(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rv_item_genre, parent, false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ITEM) {
            val item = recyclerviewItem[position]

            holder as GenreItem
            holder.bind(item)
        }
    }

    private inner class GenreItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvGenreName: TextView = itemView.findViewById(R.id.tvGenreName)

        fun bind(genre: GenreModelCommon) {
            tvGenreName.text = genre.name

            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, MoviesByGenre::class.java).apply {
                    this.putExtra("id", genre.id)
                }

                context.startActivity(intent)
            }
        }
    }
}