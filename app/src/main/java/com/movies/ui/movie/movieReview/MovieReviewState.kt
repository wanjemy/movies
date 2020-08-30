package com.movies.ui.movie.movieReview

import androidx.lifecycle.MutableLiveData
import com.movies.model.common.movie.MovieReviewModelCommon

class MovieReviewState {
    val modelReviewNewData = MutableLiveData<List<MovieReviewModelCommon>>()
    val isNoReview = MutableLiveData<Boolean>()
    val isPageTimeOut = MutableLiveData<Boolean>()
    val isPageError = MutableLiveData<Boolean>()
}