package com.movies.model.api.movie

import com.movies.base.BaseResponse

class MovieReviewResponse(val id: Int? = null, val results: List<MovieReviewModelApi>) : BaseResponse()