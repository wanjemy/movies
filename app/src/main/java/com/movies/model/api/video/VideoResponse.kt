package com.movies.model.api.video

import com.movies.base.BaseApiModel
import com.movies.base.BaseResponse
import com.movies.model.common.video.VideoResponseModelCommon

class VideoResponse(val results: List<VideoApi>?) :
    BaseResponse(),
    BaseApiModel<VideoResponseModelCommon> {

    override fun map(): VideoResponseModelCommon {
        if (results == null) {
            return VideoResponseModelCommon(listOf())
        }

        return VideoResponseModelCommon(results.map { it.map() })
    }

}