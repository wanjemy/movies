package com.movies.ui.movie.movieReview

interface MovieReviewViewModelInterface {
    fun getState(): MovieReviewState
    fun fetchMovieReview(movieId: Int)
    fun isPaginate():Boolean
}