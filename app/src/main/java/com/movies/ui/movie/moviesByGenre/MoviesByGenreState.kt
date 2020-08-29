package com.movies.ui.movie.moviesByGenre

import androidx.lifecycle.MutableLiveData
import com.movies.model.common.movie.MovieModelCommon

class MoviesByGenreState {
    val moviesNewData = MutableLiveData<List<MovieModelCommon>>()
}