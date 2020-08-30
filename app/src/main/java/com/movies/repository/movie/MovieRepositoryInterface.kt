package com.movies.repository.movie

import com.movies.base.BaseResponse
import com.movies.model.common.movie.MovieDetailModelCommon
import com.movies.model.common.movie.MovieModelCommon
import com.movies.model.common.movie.MovieReviewModelCommon
import com.movies.repository.RepositoryListener

interface MovieRepositoryInterface {
    suspend fun fetchMovies(
        genreId: Int,
        page: Int,
        repositoryListener: RepositoryListener<List<MovieModelCommon>, BaseResponse>
    )

    suspend fun fetchMovieDetail(
        movieId: Int,
        repositoryListener: RepositoryListener<MovieDetailModelCommon, BaseResponse>
    )

    suspend fun fetchMovieReview(
        movieId: Int,
        page:Int,
        repositoryListener: RepositoryListener<List<MovieReviewModelCommon>, BaseResponse>
    )
}