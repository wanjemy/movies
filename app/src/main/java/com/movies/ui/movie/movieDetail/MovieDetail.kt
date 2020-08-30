package com.movies.ui.movie.movieDetail

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.movies.R
import com.movies.base.BaseActivity
import com.movies.utils.extention.gone
import com.movies.utils.extention.setImage
import com.movies.utils.extention.visible
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.view_loading.*
import kotlinx.android.synthetic.main.view_page_error.*
import kotlinx.android.synthetic.main.view_request_timeout.*

class MovieDetail : BaseActivity() {
    private val viewModel: MovieDetailViewModelInterface = MovieDetailViewModel()
    private val movieId by lazy { intent.getIntExtra("movieId", -1) }
    private val trailerAdapter by lazy { MovieTrailerAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        initObserver()
        initOnClickEvent()

        fetchMovieDetail()
    }

    private fun initObserver() {
        val state = viewModel.getState()

        state.movieDetailData.observe(this, Observer {
            ivMovieCover.setImage(it.poster_path)
            tvMovieTitle.text = it.original_title
            tvMovieOverview.text = it.overview

            if (!it.videos.results.isNullOrEmpty()) {
                initRecyclerView()
                trailerAdapter.addNewData(it.videos.results)
            } else {
                llTrailer.gone()
            }

            initToolbar(true, it.original_title)
            flLoading.gone()
        })

        state.isPageTimeOut.observe(this, Observer {
            if (it) {
                clPageTimeout.visible()
                clPageError.gone()
            }
        })

        state.isPageError.observe(this, Observer {
            if (it) {
                clPageError.visible()
                clPageTimeout.gone()
            }
        })
    }

    private fun fetchMovieDetail() {
        if (movieId != -1) {
            viewModel.fetchMovieDetail(movieId)
        }
    }

    private fun initRecyclerView() {
        rvTrailer.apply {
            this.layoutManager = LinearLayoutManager(this@MovieDetail)
            this.adapter = trailerAdapter
        }
    }

    private fun initOnClickEvent() {
        llRetry.setOnClickListener {
            clPageTimeout.gone()
            flLoading.visible()
            fetchMovieDetail()
        }

        tvViewReview.setOnClickListener {

        }
    }
}