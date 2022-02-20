package com.example.umbrella.model.remote

import android.util.Log
import com.example.umbrella.model.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkManager {


    val weatherApi: WeatherApi by lazy {
        initRetrofit().create(WeatherApi::class.java)
    }
    val geoApi: GeoApi by lazy {
        initRetrofit().create(GeoApi::class.java)
    }

    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

}