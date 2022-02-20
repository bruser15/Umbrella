package com.example.umbrella.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.umbrella.databinding.ItemLayoutBinding
import com.example.umbrella.model.WeatherList

class WeatherAdapter (private val dataSet: List<WeatherList>,
                        private val openDisplayFragment: (WeatherList) -> Unit):
        RecyclerView.Adapter<WeatherViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            WeatherViewHolder(
                ItemLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ))

        override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
            holder.onBind(dataSet[position]){
                openDisplayFragment(it)
            }
        }

        override fun getItemCount() = dataSet.size


}