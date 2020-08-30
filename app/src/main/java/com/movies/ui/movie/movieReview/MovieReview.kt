package com.movies.ui.movie.movieReview

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.movies.R
import com.movies.base.BaseActivity
import com.movies.base.BaseRecyclerViewAdapter
import com.movies.utils.extention.gone
import com.movies.utils.extention.visible
import kotlinx.android.synthetic.main.activity_movie_review.*
import kotlinx.android.synthetic.main.view_loading.*
import kotlinx.android.synthetic.main.view_page_error.*
import kotlinx.android.synthetic.main.view_request_timeout.*

class MovieReview : BaseActivity(), BaseRecyclerViewAdapter.BaseRecyclerViewAdapterListener {
    private val movieReviewAdapter by lazy { MovieReviewAdapter(rvReview, this) }
    private val viewModel: MovieReviewViewModelInterface = MovieReviewViewModel()

    private val movieId by lazy { intent.getIntExtra("movieId", -1) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_review)

        initRecyclerView()
        initObserver()
        initToolbar(true, R.string.review)

        fetchMovieReview()
    }

    override fun onLoadMore() {
        fetchMovieReview()
    }

    override fun onRetryButtonClick() {
        fetchMovieReview()
    }

    private fun initObserver() {
        val state = viewModel.getState()

        state.modelReviewNewData.observe(this, Observer {
            flLoading.gone()
            movieReviewAdapter.addNewData(it)
        })

        state.isNoReview.observe(this, Observer {
            if (it) {
                flNoReview.visible()

                flLoading.gone()
                clPageTimeout.gone()
                clPageError.gone()
            }
        })

        state.isPageError.observe(this, Observer {
            if (it) {
                if (viewModel.isPaginate()) {
                    movieReviewAdapter.addNewData(listOf())
                } else {
                    clPageError.visible()

                    flLoading.gone()
                    clPageTimeout.gone()
                }
            }
        })

        state.isPageTimeOut.observe(this, Observer {
            if (it) {
                if (viewModel.isPaginate()) {
                    movieReviewAdapter.addRetryHolder()
                } else {
                    clPageTimeout.visible()

                    clPageError.gone()
                    flLoading.gone()
                }
            }
        })
    }

    private fun initRecyclerView() {
        rvReview.apply {
            this.layoutManager = LinearLayoutManager(this@MovieReview)
            this.adapter = movieReviewAdapter
        }
    }

    private fun fetchMovieReview() {
        if (movieId != -1) {
            viewModel.fetchMovieReview(movieId)
        }
    }
}