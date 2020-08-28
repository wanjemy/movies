package com.movies.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestClient {
    private val retrofit by lazy {
        val logging = HttpLoggingInterceptor()
        val client = OkHttpClient.Builder()

        logging.level = HttpLoggingInterceptor.Level.BODY

        client.apply {
            addInterceptor(logging)
            addNetworkInterceptor {
                val request = it.request().newBuilder().apply {
                    this.header("Authorization", Constants.AUTH)
                    this.header("Content-Type", "application/json;charset=utf8")
                    this.method(it.request().method, it.request().body)
                }

                it.proceed(request.build())
            }
        }

        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
    }

    val request by lazy {
        retrofit.create(Api::class.java)
    }
}