package com.movies.ui.movie.moviesByGenre

interface MoviesByGenreViewModelInterface {
    fun getState(): MoviesByGenreState
    fun fetchMovie(genreId: Int)
}