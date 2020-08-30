package com.movies.ui.movie.moviesByGenre

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.movies.R
import com.movies.base.BaseActivity
import com.movies.base.BaseRecyclerViewAdapter
import com.movies.utils.extention.gone
import com.movies.utils.extention.visible
import kotlinx.android.synthetic.main.activity_movies_by_genre.*
import kotlinx.android.synthetic.main.view_loading.*
import kotlinx.android.synthetic.main.view_page_error.*
import kotlinx.android.synthetic.main.view_request_timeout.*

class MoviesByGenre : BaseActivity(), BaseRecyclerViewAdapter.BaseRecyclerViewAdapterListener {
    private val genreId by lazy { intent.getIntExtra("id", -1) }
    private val moviesAdapter by lazy { MoviesAdapter(rvMovies, this) }

    private val viewModel: MoviesByGenreViewModelInterface = MoviesByGenreViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_by_genre)

        initRecyclerView()
        initObserver()
        initToolbar(true, R.string.movies)
        initOnClick()

        fetchMovies()
    }

    private fun initRecyclerView() {
        rvMovies.apply {
            this.layoutManager = GridLayoutManager(this@MoviesByGenre, 3)
            this.adapter = moviesAdapter
        }
    }

    private fun initObserver() {
        val state = viewModel.getState()

        state.moviesNewData.observe(this, Observer {
            flLoading.gone()
            moviesAdapter.addNewData(it)
        })

        state.isTimeout.observe(this, Observer {
            if (it) {
                if (state.isPagination) {
                    moviesAdapter.addRetryHolder()
                } else {
                    clPageTimeout.visible()

                    flLoading.gone()
                    clPageError.gone()
                }
            }
        })

        state.isPageError.observe(this, Observer {
            if (it) {
                if (state.isPagination) {
                    moviesAdapter.addNewData(listOf())
                } else {
                    clPageError.visible()

                    clPageTimeout.gone()
                    flLoading.gone()
                }
            }
        })
    }

    private fun initOnClick() {
        llRetry.setOnClickListener {
            flLoading.visible()
            clPageTimeout.gone()

            fetchMovies()
        }
    }

    private fun fetchMovies() {
        if (genreId != -1) {
            viewModel.fetchMovie(genreId)
        } else {
            viewModel.getState().isPageError.value = true
        }
    }

    override fun onLoadMore() {
        fetchMovies()
    }

    override fun onRetryButtonClick() {
        fetchMovies()
    }
}