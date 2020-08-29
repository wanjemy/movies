package com.movies.utils.extention

import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import com.movies.R

fun View.gone() {
    this.isVisible = false
}

fun View.visible() {
    this.isVisible = true
}