package com.example.android_project.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApiHelper {
    private var retrofit: Retrofit? = null
    fun init() {
        if (retrofit == null) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl("https://api.jsonbin.io/v3/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
    fun getRetrofitInstance(): Retrofit {
        return retrofit ?: throw IllegalStateException("Retrofit is not initialized. Call RetrofitApiHelper.init() first.")
    }
}