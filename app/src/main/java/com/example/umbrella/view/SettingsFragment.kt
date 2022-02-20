package com.example.umbrella.view

import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.umbrella.MainActivity
import com.example.umbrella.databinding.SettingsBinding
import com.example.umbrella.model.local.ZipcodeCache

class SettingsFragment: Fragment() {
    private lateinit var binding: SettingsBinding
    private val zipcodeCache by lazy {
        ZipcodeCache(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = SettingsBinding.inflate(inflater)

        binding.ibBack.setOnClickListener {
            (requireActivity() as MainActivity).viewModel.findWeather(
                ZipcodeCache(requireContext()).getSavedUnit())
            activity?.onBackPressed()
        }
        binding.llUnits.setOnClickListener { unitPrompt() }
        binding.llZip.setOnClickListener { zipPrompt() }

        binding.tvZipcode.text = zipcodeCache.getSavedZip().toString()
        binding.tvUnits.text = zipcodeCache.getSavedUnit()

        return binding.root
    }

    private fun zipPrompt() {

        val dialogBuilder = AlertDialog.Builder(requireContext())
        val input = EditText(requireContext())
        input.setHint("Enter Zipcode")
        input.inputType = InputType.TYPE_CLASS_NUMBER
        dialogBuilder.setView(input)
        dialogBuilder.setCancelable(true)
            .setPositiveButton("Enter", DialogInterface.OnClickListener {
                    dialog, id ->
                zipcodeCache.updateZip(input.text.toString().toInt())
                binding.tvZipcode.text = input.text
            })
        val alert = dialogBuilder.create()
        alert.setTitle("Update your zipcode")
        alert.show()
    }

    private fun unitPrompt() {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setCancelable(true)
            .setNegativeButton("Kelvin", DialogInterface.OnClickListener {
                    dialog, id ->
                zipcodeCache.updateUnits("Kelvin")
                binding.tvUnits.text = zipcodeCache.getSavedUnit()
            }).setPositiveButton("Celsius", DialogInterface.OnClickListener {
                    dialog, id ->
                zipcodeCache.updateUnits("Celsius")
                binding.tvUnits.text = zipcodeCache.getSavedUnit()
            }).setNeutralButton("Fahrenheit", DialogInterface.OnClickListener {
                    dialog, id ->
                zipcodeCache.updateUnits("Fahrenheit")
                binding.tvUnits.text = zipcodeCache.getSavedUnit()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("Select your units")
        alert.show()
    }
}