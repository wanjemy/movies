package com.movies.repository.genre

import com.movies.base.BaseResponse
import com.movies.model.common.genre.GenreModelCommon
import com.movies.repository.RepositoryListener

interface GenreRepositoryInterface {
    suspend fun fetchGenres(repositoryListener: RepositoryListener<List<GenreModelCommon>, BaseResponse>)
}