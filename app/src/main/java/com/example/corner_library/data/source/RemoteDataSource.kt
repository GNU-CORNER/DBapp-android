package com.example.corner_library.data.source

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteDataSource {
    // API URL
    private const val SEARCH_URL = "URL"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(SEARCH_URL)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val searchService: SearchService = retrofit.create(SearchService::class.java)
}