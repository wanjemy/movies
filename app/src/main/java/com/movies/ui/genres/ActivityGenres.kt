package com.movies.ui.genres

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.movies.R
import com.movies.base.BaseActivity
import com.movies.utils.extention.gone
import com.movies.utils.extention.visible
import kotlinx.android.synthetic.main.activity_genres.*
import kotlinx.android.synthetic.main.view_loading.*
import kotlinx.android.synthetic.main.view_page_error.*
import kotlinx.android.synthetic.main.view_page_not_found.*
import kotlinx.android.synthetic.main.view_request_timeout.*

class ActivityGenres : BaseActivity() {
    private val viewModel by lazy { GenresViewModel() }
    private val genreAdapter by lazy { GenreAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genres)

        initObserver()
        initRecyclerView()
        initToolbar(false, R.string.genres)
        initOnClick()

        viewModel.fetchGenres()
    }

    private fun initObserver() {
        val state = viewModel.state()

        state.genreNewData.observe(this, Observer {
            flLoading.gone()
            genreAdapter.addNewData(it)
        })

        state.isTimeout.observe(this, Observer {
            if (it) {
                clPageTimeout.visible()

                clPageError.gone()
                clPageNotFound.gone()
                flLoading.gone()
            }
        })

        state.isPageError.observe(this, Observer {
            if (it) {
                clPageError.visible()

                clPageTimeout.gone()
                clPageNotFound.gone()
                flLoading.gone()
            }
        })

        state.isPageNotFound.observe(this, Observer {
            if (it) {
                clPageNotFound.visible()

                clPageTimeout.gone()
                clPageError.gone()
                flLoading.gone()
            }
        })
    }

    private fun initOnClick() {
        llRetry.setOnClickListener {
            flLoading.visible()

            clPageNotFound.gone()
            clPageTimeout.gone()
            clPageError.gone()

            viewModel.fetchGenres()
        }
    }

    private fun initRecyclerView() {
        rvGenres.apply {
            this.layoutManager = LinearLayoutManager(this@ActivityGenres)
            this.adapter = genreAdapter
        }
    }
}