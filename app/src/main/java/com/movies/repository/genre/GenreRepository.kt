package com.movies.repository.genre

import android.util.Log
import com.movies.api.Constants
import com.movies.api.RestClient
import com.movies.base.BaseRepository
import com.movies.base.BaseResponse
import com.movies.model.api.GenreResponse
import com.movies.model.common.GenreModelCommon
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
                onErrorFetchGenres(genreResponse, repositoryListener)
            }
        } catch (e: Exception) {
            onErrorFetchGenres(null, repositoryListener)
        }
    }

    private suspend fun onErrorFetchGenres(
        genreResponse: Response<GenreResponse>?,
        repositoryListener: RepositoryListener<List<GenreModelCommon>, BaseResponse>
    ) {
        val response = BaseResponse()
        response.status = genreResponse?.code() ?: Constants.ERROR
        response.status_message = genreResponse?.body()?.status_message ?: ""


        withContext(Dispatchers.Main) {
            repositoryListener.onFailureListener(response)
        }
    }
}