package com.example.umbrella.model.remote

import com.example.umbrella.model.API_KEY
import com.example.umbrella.model.END_POINT_GEO
import com.example.umbrella.model.GeoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoApi {
    @GET(END_POINT_GEO)
    suspend fun getLocation(
        @Query("zip") zipcode: Int,
        @Query("appid") apiKey: String = API_KEY
    ): GeoResponse
}