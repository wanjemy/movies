package com.movies.model.common.video

import com.movies.base.BaseCommonModel

class VideoModelCommon(
    val id: String,
    val key: String,
    val name: String,
    val site: String,
    val type: String
): BaseCommonModel()