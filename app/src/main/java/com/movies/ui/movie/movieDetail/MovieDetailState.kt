package com.movies.ui.movie.movieDetail

import androidx.lifecycle.MutableLiveData
import com.movies.model.common.movie.MovieDetailModelCommon

class MovieDetailState {
    val movieDetailData = MutableLiveData<MovieDetailModelCommon>()

    val isPageTimeOut = MutableLiveData<Boolean>()
    val isPageError = MutableLiveData<Boolean>()
}