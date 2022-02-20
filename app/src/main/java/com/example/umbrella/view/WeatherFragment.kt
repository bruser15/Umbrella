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
        binding.rvDay3.layoutManager = GridLayoutManager(requireContext(), 4)
        binding.rvDay4.layoutManager = GridLayoutManager(requireContext(), 4)
        binding.rvDay5.layoutManager = GridLayoutManager(requireContext(), 4)
        binding.rvDay6.layoutManager = GridLayoutManager(requireContext(), 4)

        (requireActivity() as MainActivity).viewModel.weather.observe(requireActivity()){
            var byDayList: Pair<List<WeatherList>, List<WeatherList>>
            var date: String = it.list[0].dt_txt.substring(8,10)
            byDayList = it.list.partition{ it.dt_txt.substring(8,10).contentEquals(date) }
            Log.d("TAG", "onCreateView: $date")
            binding.rvToday.adapter = WeatherAdapter(byDayList.first.map {
                WeatherList(
                    it.main,
                    it.weather,
                    it.dt_txt)
            }) {
                //
            }
            date = byDayList.second[0].dt_txt.substring(8,10)
            byDayList = byDayList.second.partition { it.dt_txt.substring(8,10).contentEquals(date) }
            binding.rvTomorrow.adapter = WeatherAdapter(byDayList.first.map {
                WeatherList(
                    it.main,
                    it.weather,
                    it.dt_txt)
            }) {
                //
            }
            binding.tvDay3.text = byDayList.second[0].dt_txt.substring(5,10)
            date = byDayList.second[0].dt_txt.substring(8,10)
            byDayList = byDayList.second.partition { it.dt_txt.substring(8,10).contentEquals(date) }
            binding.rvDay3.adapter = WeatherAdapter(byDayList.first.map {
                WeatherList(
                    it.main,
                    it.weather,
                    it.dt_txt)
            }) {
                //
            }
            binding.tvDay4.text = byDayList.second[0].dt_txt.substring(5,10)
            date = byDayList.second[0].dt_txt.substring(8,10)
            byDayList = byDayList.second.partition { it.dt_txt.substring(8,10).contentEquals(date) }
            binding.rvDay4.adapter = WeatherAdapter(byDayList.first.map {
                WeatherList(
                    it.main,
                    it.weather,
                    it.dt_txt)
            }) {
                //
            }
            binding.tvDay5.text = byDayList.second[0].dt_txt.substring(5,10)
            date = byDayList.second[0].dt_txt.substring(8,10)
            byDayList = byDayList.second.partition { it.dt_txt.substring(8,10).contentEquals(date) }
            binding.rvDay5.adapter = WeatherAdapter(byDayList.first.map {
                WeatherList(
                    it.main,
                    it.weather,
                    it.dt_txt)
            }) {
                //
            }
            binding.tvDay6.text = byDayList.second[0].dt_txt.substring(5,10)
            date = byDayList.second[0].dt_txt.substring(8,10)
            byDayList = byDayList.second.partition { it.dt_txt.substring(8,10).contentEquals(date) }
            binding.rvDay6.adapter = WeatherAdapter(byDayList.first.map {
                WeatherList(
                    it.main,
                    it.weather,
                    it.dt_txt)
            }) {
                //
            }
        }


        return binding.root
    }
}