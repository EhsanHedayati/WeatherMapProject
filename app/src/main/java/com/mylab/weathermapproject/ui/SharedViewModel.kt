package com.mylab.weathermapproject.ui


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mylab.weathermapproject.datamodel.WeatherResponse
import com.mylab.weathermapproject.errorhandling.Resource
import com.mylab.weathermapproject.errorhandling.ResponseHandler
import com.mylab.weathermapproject.network.WeatherApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class SharedViewModel(
    private val apiService: WeatherApi,
    private val responseHandler: ResponseHandler,
) : ViewModel() {

    private val _sharedResponseWeather = MutableLiveData<Resource<WeatherResponse>>()
    val sharedResponseWeather: LiveData<Resource<WeatherResponse>>
        get() = _sharedResponseWeather

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage


    private suspend fun getWeather(lat: Double, lon: Double): Resource<WeatherResponse> {
        return try {
            responseHandler.handleSuccess(apiService.getWeather(lat, lon, WeatherApi.API_KEY))

        } catch (e: Exception) {

            responseHandler.handleException(e)

        }

    }

    fun fetchData(lat: Double, lon: Double) {
        viewModelScope.launch(Dispatchers.IO) {

            _sharedResponseWeather.postValue(getWeather(lat, lon))


        }
    }


}