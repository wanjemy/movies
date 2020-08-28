package com.movies.base

import com.movies.api.Constants

open class BaseResponse {
    val status_code: Int? = null
    var status_message: String? = null
    var status: Int = Constants.SUCCESS
}