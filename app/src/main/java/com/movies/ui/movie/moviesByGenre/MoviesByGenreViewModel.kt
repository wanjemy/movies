package com.movies.ui.movie.moviesByGenre

import androidx.lifecycle.ViewModel
import com.movies.api.Constants
import com.movies.base.BaseResponse
import com.movies.model.common.movie.MovieModelCommon
import com.movies.repository.RepositoryListener
import com.movies.repository.movie.MovieRepository
import com.movies.repository.movie.MovieRepositoryInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesByGenreViewModel : ViewModel(), MoviesByGenreViewModelInterface {
    private val viewModelState = MoviesByGenreState()
    private val movieRepository: MovieRepositoryInterface = MovieRepository()

    private var page = 0

    override fun getState(): MoviesByGenreState = viewModelState

    override fun fetchMovie(genreId: Int) {
        val repositoryListener = object : RepositoryListener<List<MovieModelCommon>, BaseResponse> {
            override fun onSuccessListener(success: List<MovieModelCommon>) {
                viewModelState.moviesNewData.value = success
            }

            override fun onFailureListener(failure: BaseResponse) {
                when (failure.status_access) {
                    Constants.TIMEOUT -> {
                        if (isPagination()) {
                            page--
                        }

                        viewModelState.isPagination = isPagination()

                        viewModelState.isTimeout.value = true
                        viewModelState.isPageError.value = false
                    }

                    else -> {
                        viewModelState.isPagination = isPagination()

                        viewModelState.isPageError.value = true
                        viewModelState.isTimeout.value = false
                    }
                }
            }
        }


        CoroutineScope(Dispatchers.IO).launch {
            page++
            movieRepository.fetchMovies(
                genreId,
                page,
                repositoryListener
            )
        }
    }

    private fun isPagination(): Boolean = page > 1

}