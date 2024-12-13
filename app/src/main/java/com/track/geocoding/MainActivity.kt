package com.track.geocoding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapImageView: ImageView = findViewById(R.id.static_map)

        val apiKey = "0e3655345a6a4f3fadc52c024ce093aa"  // Use your actual API key

        val staticMapUrl = "https://maps.geoapify.com/v1/staticmap?" +
                "style=osm-liberty&" +
                "width=600&" +
                "height=400&" +
                "center=lonlat:88.1768822,20.7516318&" +
                "zoom=14&" +
                "marker=lonlat:88.1768822,20.7516318;color:red;size:medium&" +
                "apiKey=$apiKey"

        Glide.with(this)
            .load(staticMapUrl)
            .into(mapImageView)

        val address = "Chandan Tent House, Jamshedpur, 831002"


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