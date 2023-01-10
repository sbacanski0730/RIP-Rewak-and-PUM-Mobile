package com.example.lessonplanapp

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    val okHttpClient = OkHttpClient.Builder().build()

    val api by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LessonPlanApi::class.java)
    }

    companion object{
       const val BASE_URL = "https://s1.celber.pl/"
    }
}
