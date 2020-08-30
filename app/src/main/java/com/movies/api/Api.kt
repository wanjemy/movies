package com.movies.api

import com.movies.model.api.genre.GenreResponse
import com.movies.model.api.movie.MovieDetailModelApi
import com.movies.model.api.movie.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("genre/movie/list")
    suspend fun fetchGenres(): Response<GenreResponse>

    @GET("movie/{genreId}/lists")
    suspend fun fetchMovieByGenre(
        @Path("genreId") genreId: Int,
        @Query("page") page: Int
    ): Response<MovieResponse>

    @GET("movie/{movie_id}?append_to_response=videos")
    suspend fun fetchMovieDetail(
        @Path("movie_id") movieId: Int): Response<MovieDetailModelApi>
}