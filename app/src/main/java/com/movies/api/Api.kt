package com.movies.api

import com.movies.model.api.genre.GenreResponse
import com.movies.model.api.movie.MovieDetailModelApi
import com.movies.model.api.movie.MovieResponse
import com.movies.model.api.movie.MovieReviewResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("genre/movie/list")
    suspend fun fetchGenres(): Response<GenreResponse>

    @GET("discover/movie")
    suspend fun fetchMovieByGenre(
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int
    ): Response<MovieResponse>

    @GET("movie/{movie_id}?append_to_response=videos")
    suspend fun fetchMovieDetail(
        @Path("movie_id") movieId: Int
    ): Response<MovieDetailModelApi>

    @GET("movie/{movie_id}/reviews")
    suspend fun fetchMovieReview(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int
    ): Response<MovieReviewResponse>
}