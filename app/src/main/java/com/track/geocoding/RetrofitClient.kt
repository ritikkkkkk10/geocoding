package com.track.geocoding

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {
    private const val BASE_URL = "https://api.geoapify.com/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())  // This converts JSON data into Kotlin objects
            .build()
    }

    val geocodingService: GeocodingService by lazy {
        retrofit.create(GeocodingService::class.java)
    }
}