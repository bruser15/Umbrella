package com.example.umbrella.model

data class WeatherResponse(
    val list: List<WeatherList>
)

data class WeatherList(
    val main: WeatherMain,
    val weather: List<WeatherItem>,
    val dt_txt: String
)

data class WeatherMain(
    val temp: Float
)

data class WeatherItem(
    val main: String,
    val icon: String
)