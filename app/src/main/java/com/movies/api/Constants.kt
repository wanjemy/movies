package com.movies.api

object Constants {
    val BASE_URL = "https://api.themoviedb.org/3/"
    val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185"
    val AUTH =
        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiMWNkNWNmMjJkNThhNDkzYmQ1ZWVkMThhOGUzOGI1YSIsInN1YiI6IjVmNDdjYzkwODEzY2I2MDAzNThjNjQ3OCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.ltEc1Q3-GVkR-IZNQEk4L9YQuBEt-CNsTMXjU74aI90"

    val SUCCESS = 200
    val UNAUTHORIZE = 401
    val NOT_FOUND = 5
    val TIMEOUT = 408
    val ERROR = 500
}