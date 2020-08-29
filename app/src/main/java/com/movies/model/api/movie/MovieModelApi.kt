package com.movies.model.api.movie

import com.movies.api.Constants
import com.movies.base.BaseApiModel
import com.movies.model.common.movie.MovieModelCommon

class MovieModelApi(
    val id: Long? = null,
    val name: String? = null,
    val poster_path: String? = null
) : BaseApiModel<MovieModelCommon> {

    override fun map(): MovieModelCommon =
        MovieModelCommon(id ?: 0,
            name ?: "",
            poster_path ?: "${Constants.BASE_IMAGE_URL}$poster_path")
}