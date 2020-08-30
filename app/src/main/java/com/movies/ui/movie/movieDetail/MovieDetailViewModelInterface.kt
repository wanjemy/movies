package com.movies.ui.movie.movieDetail

interface MovieDetailViewModelInterface {
    fun getState(): MovieDetailState
    fun fetchMovieDetail(movieId: Int)
}