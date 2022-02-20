package com.example.umbrella.model

import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getLocation(zipCode: Int): Flow<GeoResponse>
    fun getWeather(): Flow<WeatherResponse>
}