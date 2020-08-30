package com.movies.ui.genres

import androidx.lifecycle.ViewModel
import com.movies.api.Constants
import com.movies.base.BaseResponse
import com.movies.model.common.genre.GenreModelCommon
import com.movies.repository.RepositoryListener
import com.movies.repository.genre.GenreRepository
import com.movies.repository.genre.GenreRepositoryInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GenresViewModel() : ViewModel(), GenreViewModelInterface {
    private val genreState = GenreState()
    private val genreRepository: GenreRepositoryInterface = GenreRepository()

    override fun state(): GenreState = genreState

    override fun fetchGenres() {
        val repositoryListener =
            object : RepositoryListener<List<GenreModelCommon>, BaseResponse> {
                override fun onSuccessListener(success: List<GenreModelCommon>) {
                    genreState.genreNewData.value = success
                }

                override fun onFailureListener(failure: BaseResponse) {

                    when (failure.status_access) {
                        Constants.TIMEOUT -> {
                            genreState.isTimeout.value = true

                            genreState.isPageNotFound.value = false
                            genreState.isPageError.value = false
                        }

                        Constants.NOT_FOUND -> {
                            genreState.isPageNotFound.value = true

                            genreState.isPageError.value = false
                            genreState.isTimeout.value = false
                        }

                        else -> {
                            genreState.isPageError.value = true

                            genreState.isTimeout.value = false
                            genreState.isPageNotFound.value = false
                        }
                    }
                }
            }

        CoroutineScope(Dispatchers.IO).launch {
            genreRepository.fetchGenres(repositoryListener)
        }
    }
}
