package com.movies.ui.genres

interface GenreViewModelInterface {
    fun state(): GenreState
    fun fetchGenres()
}