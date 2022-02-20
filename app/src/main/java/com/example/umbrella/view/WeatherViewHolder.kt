package com.example.umbrella.view

import androidx.recyclerview.widget.RecyclerView
import com.example.umbrella.databinding.ItemLayoutBinding
import com.example.umbrella.model.WeatherList
import com.squareup.picasso.Picasso

class WeatherViewHolder (private val binding: ItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(weather: WeatherList, callback: (WeatherList) -> Unit){
        binding.tvTime.text =
            weather.dt_txt.substring(11,16)
        binding.tvItemTemp.text =
            weather.main.temp.toInt().toString()
        val url = StringBuilder("http://openweathermap.org/img/wn/")
        url.append(weather.weather[0].icon)
        url.append("@2x.png")
        Picasso.get()
            .load(url.toString())
            .into(binding.ivItemIcon)

    }
}