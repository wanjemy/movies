package com.movies.repository.movie

import com.movies.base.BaseResponse
import com.movies.model.common.movie.MovieModelCommon
import com.movies.repository.RepositoryListener

interface MovieRepositoryInterface {
    suspend fun fetchMovies(
        genreId: Int,
        page: Int,
        repositoryListener: RepositoryListener<List<MovieModelCommon>, BaseResponse>
    )
}