package com.movies.ui.splashScreen

import android.content.Intent
import android.os.Bundle
import com.movies.R
import com.movies.base.BaseActivity
import com.movies.ui.genres.ActivityGenres
import kotlinx.coroutines.*

class SplashScreen : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)

            val intent = Intent(this@SplashScreen, ActivityGenres::class.java)
            startActivity(intent)
            finish()
        }
    }
}