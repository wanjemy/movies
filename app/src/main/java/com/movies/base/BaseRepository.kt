package com.movies.base

abstract class BaseRepository {
    fun <T> mappingResponse(response: List<BaseApiModel<T>>): List<T> {
        return response.map { it.map() }
    }
}