package com.movies.utils.extention

import android.view.View
import androidx.core.view.isVisible

fun View.gone() {
    this.isVisible = false
}

fun View.visible() {
    this.isVisible = true
}