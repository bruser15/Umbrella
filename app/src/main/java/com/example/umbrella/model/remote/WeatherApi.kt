package com.example.umbrella.model.remote

import com.example.umbrella.model.API_KEY
import com.example.umbrella.model.END_POINT
import com.example.umbrella.model.UNIT_K
import com.example.umbrella.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET(END_POINT)
    suspend fun getWeather(
        @Query("lat") lat: Float,
        @Query("lon") lon: Float,
        @Query("units") units: String,
        @Query("appid") apiKey: String = API_KEY
    ): WeatherResponse

}