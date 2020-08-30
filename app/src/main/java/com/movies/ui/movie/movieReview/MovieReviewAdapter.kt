package com.movies.ui.movie.movieReview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.movies.R
import com.movies.base.BaseRecyclerViewAdapter
import com.movies.model.common.movie.MovieReviewModelCommon

class MovieReviewAdapter(
    recyclerView: RecyclerView,
    private val listener: BaseRecyclerViewAdapterListener
) : BaseRecyclerViewAdapter<MovieReviewModelCommon>(recyclerView) {
    override fun generateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ReviewItem(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rv_item_movie_review,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ITEM) {
            holder as ReviewItem
            val item = recyclerviewItem[position]

            holder.bind(item)
        }

    }

    override fun onLoadMore() {
        super.onLoadMore()
        listener.onLoadMore()
    }

    override fun onClickButtonRetry() {
        super.onClickButtonRetry()
        listener.onRetryButtonClick()
    }

    private inner class ReviewItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvAuthor: TextView = itemView.findViewById(R.id.tvAuthor)
        private val tvContent: TextView = itemView.findViewById(R.id.tvContent)

        fun bind(review: MovieReviewModelCommon) {
            tvAuthor.text = review.author
            tvContent.text = review.content
        }
    }

}