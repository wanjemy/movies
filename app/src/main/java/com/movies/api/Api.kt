package com.movies.api

import com.movies.model.api.GenreResponse
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("genre/movie/list")
    suspend fun fetchGenres(): Response<GenreResponse>
}