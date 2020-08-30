package com.movies.utils.extention

import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.movies.R
import com.movies.api.Constants

fun ImageView.setImage(link: String?, defaultImage: Int = R.drawable.ic_no_image) {
    if (!link.isNullOrBlank()) {
        Glide.with(this).load("${Constants.BASE_IMAGE_URL}$link").into(this)
    } else {
        this.setImageResource(defaultImage)
    }
}