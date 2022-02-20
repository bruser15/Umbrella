package com.example.umbrella.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.umbrella.MainActivity
import com.example.umbrella.databinding.WeatherFragmentLayoutBinding
import com.example.umbrella.model.WeatherList

class WeatherFragment: Fragment() {

    private lateinit var binding: WeatherFragmentLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = WeatherFragmentLayoutBinding.inflate(inflater)

        binding.rvToday.layoutManager = GridLayoutManager(requireContext(), 4)
        binding.rvTomorrow.layoutManager = GridLayoutManager(requireContext(), 4)

        (requireActivity() as MainActivity).viewModel.weather.observe(requireActivity()){
            Log.d("WeatherFragment", "onCreateView: ")
            binding.rvToday.adapter = WeatherAdapter(it.list.map {
                WeatherList(
                    it.main,
                    it.weather,
                    it.dt_txt)
            }) {
                //
            }
//            binding.rvTomorrow.adapter = WeatherAdapter(it.list.map {
//                WeatherList(
//                    it.main,
//                    it.weather,
//                    it.dt_txt)
//            }) {
//                //
//            }
        }


        return binding.root
    }
}