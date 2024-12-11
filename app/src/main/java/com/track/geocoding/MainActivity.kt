package com.track.geocoding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val address = "Chandan Tent House, Jamshedpur, 831002"
        val apiKey = "0e3655345a6a4f3fadc52c024ce093aa"  // Use your actual API key

        latLon(address, apiKey)
    }

    private fun latLon(address: String, apiKey: String) {
        RetrofitClient.geocodingService.getLatLong(address, apiKey).enqueue(object : Callback<GeocodingResponse> {
            override fun onResponse(call: Call<GeocodingResponse>, response: Response<GeocodingResponse>) {
                if (response.isSuccessful) {
                    val geocodingResponse = response.body()
                    val lat = geocodingResponse?.features?.firstOrNull()?.properties?.lat
                    val lon = geocodingResponse?.features?.firstOrNull()?.properties?.lon
                    Toast.makeText(this@MainActivity, "Lat: $lat, Lon: $lon", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@MainActivity, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<GeocodingResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}