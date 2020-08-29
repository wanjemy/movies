package com.movies.model.api.genre

import com.movies.base.BaseApiModel
import com.movies.model.common.genre.GenreModelCommon

data class GenreModelApi(val id: Int?, val name: String?) : BaseApiModel<GenreModelCommon> {
    override fun map(): GenreModelCommon =
        GenreModelCommon(
            id ?: -1,
            name ?: ""
        )

}