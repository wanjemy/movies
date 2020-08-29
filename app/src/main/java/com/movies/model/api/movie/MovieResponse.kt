package com.movies.model.api.movie

import com.movies.base.BaseApiModel
import com.movies.base.BaseResponse

class MovieResponse(val id:Int? = null, val result:List<MovieModelApi>): BaseResponse()