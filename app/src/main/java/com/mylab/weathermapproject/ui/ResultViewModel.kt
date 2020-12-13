package com.mylab.weathermapproject.ui

import androidx.lifecycle.ViewModel

class ResultViewModel : ViewModel() {


    fun convertedTemp(temp: Double):Int {
        return (temp - 273.15).toInt()
    }





}