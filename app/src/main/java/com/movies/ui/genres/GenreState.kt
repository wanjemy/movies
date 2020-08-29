package com.movies.ui.genres

import androidx.lifecycle.MutableLiveData
import com.movies.model.common.genre.GenreModelCommon

class GenreState {
    val genreNewData = MutableLiveData<List<GenreModelCommon>>()
    val isTimeout = MutableLiveData<Boolean>()
    val isPageNotFound = MutableLiveData<Boolean>()
    val isPageError = MutableLiveData<Boolean>()
}