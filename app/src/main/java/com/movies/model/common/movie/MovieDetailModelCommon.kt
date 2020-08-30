package com.movies.model.common.movie

import com.movies.base.BaseCommonModel
import com.movies.model.common.video.VideoResponseModelCommon

class MovieDetailModelCommon(
    val original_title: String,
    val poster_path: String,
    val overview: String,
    val videos: VideoResponseModelCommon
) : BaseCommonModel()