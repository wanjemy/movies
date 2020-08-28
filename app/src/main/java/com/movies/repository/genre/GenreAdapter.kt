package com.movies.repository.genre

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.movies.R
import com.movies.base.BaseRecyclerViewAdapter
import com.movies.model.common.GenreModelCommon

class GenreAdapter : BaseRecyclerViewAdapter<GenreModelCommon>() {

    override fun generateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        GenreItem(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rv_item_genre, parent, false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = recyclerviewItem[position]

        if (item.recyclerViewViewType == ITEM) {
            holder as GenreItem
            holder.bind(item)
        }
    }

    private inner class GenreItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvGenreName: TextView = itemView.findViewById(R.id.tvGenreName)

        fun bind(genre: GenreModelCommon) {
            tvGenreName.text = genre.name
        }
    }
}