package com.track.geocoding

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

data class GeocodingResponse(
    val features: List<Feature>
)

data class Feature(
    val properties: Properties
)

data class Properties(
    val lat: Double,
    val lon: Double
)

interface GeocodingService {
    @GET("/v1/geocode/search")
    fun getLatLong(
        @Query("text") address: String,
        @Query("apiKey") apiKey: String
    ): Call<GeocodingResponse>
}