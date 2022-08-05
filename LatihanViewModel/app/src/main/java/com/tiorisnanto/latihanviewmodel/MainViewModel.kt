package com.tiorisnanto.latihanviewmodel

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var result = 0

    fun calculateVolume(width: String, height: String, length: String) {
        result = width.toInt() * height.toInt() * length.toInt()
    }
}