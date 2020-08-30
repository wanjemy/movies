package com.movies.model.api.movie

import com.movies.base.BaseApiModel
import com.movies.base.BaseResponse
import com.movies.model.api.video.VideoResponse
import com.movies.model.common.movie.MovieDetailModelCommon
import com.movies.model.common.video.VideoResponseModelCommon

data class MovieDetailModelApi(
    val original_title: String?,
    val poster_path: String?,
    val overview: String?,
    val videos: VideoResponse?
) : BaseApiModel<MovieDetailModelCommon>, BaseResponse() {

    override fun map(): MovieDetailModelCommon =
        MovieDetailModelCommon(
            original_title ?: "",
            poster_path ?: "",
            overview ?: "",
            convertToVideoResponseCommon()
        )

    private fun convertToVideoResponseCommon(): VideoResponseModelCommon {
        if (videos == null) {
            return VideoResponseModelCommon(listOf())
        }

        return videos.map()
    }
}