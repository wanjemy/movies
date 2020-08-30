package com.movies.ui.movie.movieDetail

import androidx.lifecycle.ViewModel
import com.movies.api.Constants
import com.movies.base.BaseResponse
import com.movies.model.common.movie.MovieDetailModelCommon
import com.movies.repository.RepositoryListener
import com.movies.repository.movie.MovieRepository
import com.movies.repository.movie.MovieRepositoryInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailViewModel :
    ViewModel(),
    MovieDetailViewModelInterface {
    private val viewModelState = MovieDetailState()
    private val repository: MovieRepositoryInterface = MovieRepository()

    override fun getState(): MovieDetailState =
        viewModelState

    override fun fetchMovieDetail(movieId: Int) {
        val repositoryListener = object : RepositoryListener<MovieDetailModelCommon, BaseResponse> {
            override fun onSuccessListener(success: MovieDetailModelCommon) {
                viewModelState.movieDetailData.value = success
            }

            override fun onFailureListener(failure: BaseResponse) {
                when (failure.status_access) {
                    Constants.TIMEOUT -> {
                        viewModelState.isPageTimeOut.value = true
                        viewModelState.isPageError.value = false
                    }

                    else -> {
                        viewModelState.isPageError.value = true
                        viewModelState.isPageTimeOut.value = false
                    }
                }
            }

        }

        CoroutineScope(Dispatchers.IO).launch {
            repository.fetchMovieDetail(movieId, repositoryListener)
        }
    }
}