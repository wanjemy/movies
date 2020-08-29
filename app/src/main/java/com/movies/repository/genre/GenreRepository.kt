package com.movies.repository.genre

import com.movies.api.Constants
import com.movies.api.RestClient
import com.movies.base.BaseRepository
import com.movies.base.BaseResponse
import com.movies.model.api.genre.GenreResponse
import com.movies.model.common.genre.GenreModelCommon
import com.movies.repository.RepositoryListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception

class GenreRepository : BaseRepository(), GenreRepositoryInterface {
    override suspend fun fetchGenres(repositoryListener: RepositoryListener<List<GenreModelCommon>, BaseResponse>) {
        try {
            val genreResponse = RestClient.request.fetchGenres()

            if (genreResponse.isSuccessful) {
                val response = genreResponse.body()

                response?.genres?.let {
                    withContext(Dispatchers.Main) {
                        repositoryListener.onSuccessListener(
                            mappingResponse(it)
                        )
                    }
                }

            } else {
                onErrorRequest(genreResponse, repositoryListener)
            }
        } catch (e: Exception) {
            onFatalError(repositoryListener)
        }
    }
}