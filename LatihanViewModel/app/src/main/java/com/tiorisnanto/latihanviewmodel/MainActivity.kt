package com.tiorisnanto.latihanviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.tiorisnanto.latihanviewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        displayResult()

        binding.btnCalculate.setOnClickListener {
            val width = binding.edtWidth.text.toString()
            val height = binding.edtHeight.text.toString()
            val lenght = binding.edtLength.text.toString()

            when {
                width.isEmpty() -> binding.tvResult.text = "Please enter width"
                height.isEmpty() -> binding.tvResult.text = "Please enter height"
                lenght.isEmpty() -> binding.tvResult.text = "Please enter length"
                else -> {
                    viewModel.calculateVolume(width, height, lenght)
                    displayResult()
                }
            }
        }
    }

    private fun displayResult() {
        binding.tvResult.text = viewModel.result.toString()
    }
}