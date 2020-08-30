package com.movies.base

import com.movies.api.Constants
import com.movies.repository.RepositoryListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class BaseRepository {
    protected fun <T> mappingResponse(response: List<BaseApiModel<T>>): List<T> {
        return response.map { it.map() }
    }

    protected suspend fun <T: BaseResponse> onErrorRequest(
        baseResponse: Response<T>,
        repositoryListener: RepositoryListener<*, BaseResponse>
    ) {
        val response = BaseResponse()
        response.status_access = baseResponse.code()
        response.status_message = baseResponse.body()?.status_message ?: ""


        withContext(Dispatchers.Main) {
            repositoryListener.onFailureListener(response)
        }
    }

    protected suspend fun onFatalError(repositoryListener: RepositoryListener<*, BaseResponse>) {
        val response = BaseResponse()
        response.status_access = Constants.ERROR

        withContext(Dispatchers.Main) {
            repositoryListener.onFailureListener(response)
        }
    }

    protected suspend fun onTimeOUt(repositoryListener: RepositoryListener<*, BaseResponse>) {
        val response = BaseResponse()
        response.status_access = Constants.TIMEOUT

        withContext(Dispatchers.Main) {
            repositoryListener.onFailureListener(response)
        }
    }
}