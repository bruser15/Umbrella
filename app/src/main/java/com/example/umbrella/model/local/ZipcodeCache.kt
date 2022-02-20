package com.example.umbrella.model.local

import android.content.Context
import androidx.core.content.edit
import com.example.umbrella.model.UNIT_C
import com.example.umbrella.model.UNIT_F
import com.example.umbrella.model.UNIT_K

class ZipcodeCache(private val context: Context) {

    companion object{
        private const val CONNECTIVITY_SP = "ZipcodeRequest"
        private const val ZIP_REQUEST = "LastZip"
        private const val UNIT_REQUEST = "LastUnit"
    }

    fun getSavedZip(): Int {
        return context.getSharedPreferences(CONNECTIVITY_SP, Context.MODE_PRIVATE)
            .getInt(ZIP_REQUEST, 0)
    }
    fun getSavedUnit(): String{
        return context.getSharedPreferences(CONNECTIVITY_SP, Context.MODE_PRIVATE)
            .getString(UNIT_REQUEST, null).toString()
    }

    fun updateZip(zip:Int){
        context.getSharedPreferences(CONNECTIVITY_SP, Context.MODE_PRIVATE)
            .edit {
                putInt(ZIP_REQUEST, zip)
            }
    }
    fun updateUnits(unit:String){
        context.getSharedPreferences(CONNECTIVITY_SP, Context.MODE_PRIVATE)
            .edit {
                when(unit){
                    "Kelvin" -> putString(UNIT_REQUEST, UNIT_K)
                    "Celsius" -> putString(UNIT_REQUEST, UNIT_C)
                    "Fahrenheit" -> putString(UNIT_REQUEST, UNIT_F)
                }
            }
    }
}