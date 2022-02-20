package com.example.umbrella.modelview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.umbrella.model.Repository
import kotlin.coroutines.CoroutineContext

class WeatherProvider (
                       private val coroutineContext: CoroutineContext
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return WeatherViewModel(coroutineContext) as T
        }

}