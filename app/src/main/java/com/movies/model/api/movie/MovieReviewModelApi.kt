package com.movies.model.api.movie

import com.movies.base.BaseApiModel
import com.movies.model.common.movie.MovieReviewModelCommon

class MovieReviewModelApi(
    val author: String?,
    val content: String?
) : BaseApiModel<MovieReviewModelCommon> {

    override fun map(): MovieReviewModelCommon {
        return MovieReviewModelCommon(
            author ?: "",
            content ?: ""
        )
    }

}