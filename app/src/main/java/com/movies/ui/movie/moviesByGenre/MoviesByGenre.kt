package com.movies.ui.movie.moviesByGenre

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.movies.R
import com.movies.base.BaseActivity
import kotlinx.android.synthetic.main.activity_movies_by_genre.*

class MoviesByGenre : BaseActivity() {
    private val genreId by lazy { intent.getIntExtra("id", -1) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_by_genre)

        initRecyclerView()

        fetchMovies()
    }

    private fun initRecyclerView() {
        rvMovies.apply {
            this.layoutManager = LinearLayoutManager(this@MoviesByGenre)
//            this.adapter =
        }
    }

    private fun fetchMovies() {
        if (genreId != -1) {

        }
    }
}