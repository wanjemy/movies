package com.movies.repository.movie

import com.movies.api.RestClient
import com.movies.base.BaseRepository
import com.movies.base.BaseResponse
import com.movies.model.common.movie.MovieDetailModelCommon
import com.movies.model.common.movie.MovieModelCommon
import com.movies.repository.RepositoryListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.net.SocketTimeoutException

class MovieRepository : BaseRepository(), MovieRepositoryInterface {
    override suspend fun fetchMovies(
        genreId: Int,
        page: Int,
        repositoryListener: RepositoryListener<List<MovieModelCommon>, BaseResponse>
    ) {
        try {
            val movieResponse = RestClient.request.fetchMovieByGenre(genreId, page)

            if (movieResponse.isSuccessful) {
                val response = movieResponse.body()

                if (response?.status_code == null) {
                    response?.results?.let {
                        withContext(Dispatchers.Main) {
                            repositoryListener.onSuccessListener(
                                mappingResponse(it)
                            )
                        }
                    }
                } else {
                    onFatalError(repositoryListener)
                }

            } else {
                onErrorRequest(movieResponse, repositoryListener)
            }
        } catch (e: SocketTimeoutException) {
            onTimeOUt(repositoryListener)
        } catch (e: Exception) {
            onFatalError(repositoryListener)
        }
    }

    override suspend fun fetchMovieDetail(
        movieId: Int,
        repositoryListener: RepositoryListener<MovieDetailModelCommon, BaseResponse>
    ) {

        try {
            val movieDetailResponse = RestClient.request.fetchMovieDetail(movieId)

            if (movieDetailResponse.isSuccessful) {
                val movieDetail = movieDetailResponse.body()
                movieDetail?.let {
                    withContext(Dispatchers.Main) {
                        repositoryListener.onSuccessListener(it.map())
                    }
                }

            } else {
                onErrorRequest(movieDetailResponse, repositoryListener)
            }
        } catch (e: SocketTimeoutException) {
            onTimeOUt(repositoryListener)
        } catch (e: Exception) {
            onFatalError(repositoryListener)
        }

    }
}