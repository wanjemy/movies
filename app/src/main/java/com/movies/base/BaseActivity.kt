package com.movies.base

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.view_toolbar.*

abstract class BaseActivity : AppCompatActivity() {
    protected fun initToolbar(isBackButtonVisible: Boolean, toolbarTitle: Int) {
        ivToolbarBack.isVisible = isBackButtonVisible
        tvToolbar.setText(toolbarTitle)
        initBackPressToolbar()
    }

    protected fun initToolbar(isBackButtonVisible: Boolean, toolbarTitle: String) {
        ivToolbarBack.isVisible = isBackButtonVisible
        tvToolbar.text = toolbarTitle

        initBackPressToolbar()
    }

    private fun initBackPressToolbar() {
        ivToolbarBack.setOnClickListener {
            onBackPressed()
        }
    }
}