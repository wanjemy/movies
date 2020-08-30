package com.movies.ui.movie.movieReview

import androidx.lifecycle.ViewModel
import com.movies.api.Constants
import com.movies.base.BaseResponse
import com.movies.model.common.movie.MovieReviewModelCommon
import com.movies.repository.RepositoryListener
import com.movies.repository.movie.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieReviewViewModel : ViewModel(), MovieReviewViewModelInterface {
    private val repository: MovieRepository by lazy { MovieRepository() }
    private val movieReviewState = MovieReviewState()

    private var page = 0

    override fun getState(): MovieReviewState =
        movieReviewState

    override fun isPaginate(): Boolean = page > 1

    override fun fetchMovieReview(movieId: Int) {
        val repositoryListener =
            object : RepositoryListener<List<MovieReviewModelCommon>, BaseResponse> {
                override fun onSuccessListener(success: List<MovieReviewModelCommon>) {
                    if (page == 1 && success.isEmpty()) {
                        movieReviewState.isNoReview.value = true
                    } else {
                        movieReviewState.modelReviewNewData.value = success
                    }
                }

                override fun onFailureListener(failure: BaseResponse) {
                    when (failure.status_access) {
                        Constants.TIMEOUT -> {
                            movieReviewState.isPageTimeOut.value = true
                            movieReviewState.isPageError.value = false
                        }

                        else -> {
                            movieReviewState.isPageError.value = true
                            movieReviewState.isPageTimeOut.value = false
                        }
                    }
                }
            }

        page++

        CoroutineScope(Dispatchers.IO).launch {
            repository.fetchMovieReview(movieId, page, repositoryListener)
        }
    }
}