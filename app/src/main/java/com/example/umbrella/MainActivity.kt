package com.example.umbrella

import android.content.DialogInterface
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ResourcesCompat
import com.example.umbrella.databinding.ActivityMainBinding
import com.example.umbrella.model.UNIT_C
import com.example.umbrella.model.UNIT_F
import com.example.umbrella.model.UNIT_K
import com.example.umbrella.model.local.ZipcodeCache
import com.example.umbrella.modelview.WeatherProvider
import com.example.umbrella.modelview.WeatherViewModel
import com.example.umbrella.view.SettingsFragment
import com.example.umbrella.view.WeatherFragment
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity() {
    lateinit var viewModelProvider: WeatherProvider

    lateinit var viewModel : WeatherViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModelProvider = WeatherProvider(Dispatchers.IO)
        viewModel = viewModelProvider.create(WeatherViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(binding.toolbar)
        supportFragmentManager.beginTransaction()
            .add(R.id.container, WeatherFragment())
            .commit()
        val zipcodeCache = ZipcodeCache(this)
        val units = zipcodeCache.getSavedUnit()
        if(zipcodeCache.getSavedZip() == 0){
            val dialogBuilder = AlertDialog.Builder(this)
            val input = EditText(this)
            input.setHint("Enter Zipcode")
            input.inputType = InputType.TYPE_CLASS_NUMBER
            dialogBuilder.setView(input)
            dialogBuilder.setCancelable(false)
                .setPositiveButton("Enter", DialogInterface.OnClickListener {
                        dialog, id ->
                    zipcodeCache.updateZip(input.text.toString().toInt())
                    viewModel.findLocation(input.text.toString().toInt())

                })
            val alert = dialogBuilder.create()
            alert.setTitle("Input your zipcode")
            alert.show()
        }
        else{
            viewModel.findLocation(zipcodeCache.getSavedZip())
        }
        viewModel.geo.observe(this){
            findViewById<TextView>(R.id.tv_location).text = viewModel.geo.value?.name ?: ""
            viewModel.findWeather(zipcodeCache.getSavedUnit())
        }

        viewModel.weather.observe(this){
            if(
                (units.contentEquals(UNIT_F) && it.list[0].main.temp > 60F)
                || (units.contentEquals(UNIT_C) && it.list[0].main.temp > 15.5F)
                || (units.contentEquals(UNIT_K) && it.list[0].main.temp > 288.7F)
            ){
                // it's getting to this code correctly, but the color isn't changing
                //Log.d("SETCOLOR ", "onCreate: ")
                supportActionBar?.setBackgroundDrawable(
                    ColorDrawable(ResourcesCompat.getColor(resources, R.color.warm, theme))
                )
                binding.toolbar.background =
                    ColorDrawable(ResourcesCompat.getColor(resources, R.color.warm, theme))
            }
            else{
                binding.toolbar.setBackgroundColor(resources.getColor(R.color.cool))
            }
            findViewById<TextView>(R.id.tv_temperature).text = viewModel.weather.value?.list?.get(0)?.main?.temp?.toInt()
                .toString()
            findViewById<TextView>(R.id.tv_weather).text =
                viewModel.weather.value?.list?.get(0)?.weather?.get(0)?.main ?: ""
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    fun openSettings(menuItem: MenuItem){
        if(menuItem.title.equals("Settings"))
        supportFragmentManager.beginTransaction()
            .add(android.R.id.content, SettingsFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun onResume() {
        super.onResume()
        val zipcodeCache = ZipcodeCache(this)
        if(zipcodeCache.getSavedZip() != 0) {
            viewModel.findWeather(zipcodeCache.getSavedUnit())
        }
    }
}