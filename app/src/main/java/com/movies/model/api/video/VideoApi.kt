package com.movies.model.api.video

import com.movies.base.BaseApiModel
import com.movies.model.common.video.VideoModelCommon

data class VideoApi(
    val id: String?,
    val key: String?,
    val name: String?,
    val site: String?,
    val type: String?
) : BaseApiModel<VideoModelCommon> {

    override fun map(): VideoModelCommon =
        VideoModelCommon(
            id ?: "",
            key ?: "",
            name ?: "",
            site ?: "",
            type ?: ""
        )

}