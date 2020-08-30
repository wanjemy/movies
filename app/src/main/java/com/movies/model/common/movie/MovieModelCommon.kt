package com.movies.model.common.movie

import com.movies.base.BaseCommonModel

class MovieModelCommon(
    val id: Int,
    val name: String,
    val poster_path: String
) : BaseCommonModel()