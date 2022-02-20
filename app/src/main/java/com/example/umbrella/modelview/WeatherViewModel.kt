package com.example.umbrella.modelview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.umbrella.model.GeoResponse
import com.example.umbrella.model.WeatherResponse
import com.example.umbrella.model.remote.NetworkManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class WeatherViewModel(
    private val coroutineContext: CoroutineContext
): ViewModel() {

    private val job = CoroutineScope(coroutineContext)
    private val _weather = MutableLiveData<WeatherResponse>()
    val weather: LiveData<WeatherResponse>
        get() = _weather
    private val _geo = MutableLiveData<GeoResponse>()
    val geo: LiveData<GeoResponse>
        get() = _geo

    fun findLocation(zip: Int){
        job.launch {
            _geo.postValue(NetworkManager.geoApi.getLocation(zip))
            Log.d("ViewModel ", "findLocation: ${NetworkManager.geoApi.getLocation(zip).name}")
        }
    }

    fun findWeather(units: String){
        job.launch {
            geo.value?.let {
                geo.value?.lon?.let { it1 ->
                    geo.value?.lat?.let { it2 ->
                        _weather.postValue(NetworkManager.weatherApi.getWeather(it2, it1, units))
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel("ViewModel onCleared")
    }
}