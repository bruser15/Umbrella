package com.example.umbrella.model

data class GeoResponse(
    val name: String,
    val lat: Float,
    val lon: Float,
    val state: String?,
    val country: String
)
