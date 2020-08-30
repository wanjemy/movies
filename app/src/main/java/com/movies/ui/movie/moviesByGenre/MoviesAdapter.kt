package com.movies.ui.movie.moviesByGenre

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.movies.R
import com.movies.base.BaseRecyclerViewAdapter
import com.movies.model.common.movie.MovieModelCommon
import com.movies.ui.movie.movieDetail.MovieDetail
import com.movies.utils.extention.setImage

class MoviesAdapter(
    recyclerView: RecyclerView,
    private val adapterListener: BaseRecyclerViewAdapterListener
) :
    BaseRecyclerViewAdapter<MovieModelCommon>(recyclerView) {
    override fun generateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MovieItem(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_item_movie, parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ITEM) {
            val item = recyclerviewItem[position]
            holder as MovieItem
            holder.bind(item)
        }
    }

    override fun onLoadMore() {
        super.onLoadMore()
        adapterListener.onLoadMore()
    }

    private inner class MovieItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivMovieCover: ImageView = itemView.findViewById(R.id.ivMovieCover)
        private val tvMovieName: TextView = itemView.findViewById(R.id.tvMovieName)

        fun bind(movie: MovieModelCommon) {
            tvMovieName.text = movie.name
            ivMovieCover.setImage(movie.poster_path)

            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, MovieDetail::class.java).apply {
                    this.putExtra("movieId", movie.id)
                }

                context.startActivity(intent)
            }
        }
    }
}