package com.movies.repository.movie

import com.movies.api.RestClient
import com.movies.base.BaseRepository
import com.movies.base.BaseResponse
import com.movies.model.common.movie.MovieModelCommon
import com.movies.repository.RepositoryListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

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

                response?.result?.let {
                    withContext(Dispatchers.Main) {
                        repositoryListener.onSuccessListener(
                            mappingResponse(it)
                        )
                    }
                }
            } else {
                onErrorRequest(movieResponse, repositoryListener)
            }
        } catch (e: Exception) {
            onFatalError(repositoryListener)
        }
    }
}