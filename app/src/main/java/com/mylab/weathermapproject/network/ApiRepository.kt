package com.mylab.weathermapproject.network

import com.mylab.weathermapproject.datamodel.WeatherResponse
import com.mylab.weathermapproject.errorhandling.Resource
import com.mylab.weathermapproject.errorhandling.ResponseHandler
import java.lang.Exception

class ApiRepository(private val apiService:WeatherApi, private val responseHandler: ResponseHandler) {

     suspend fun getWeather(lat: Double, lon: Double): Resource<WeatherResponse> {
        return try {
            responseHandler.handleSuccess(apiService.getWeather(lat, lon, WeatherApi.API_KEY))

        } catch (e: Exception) {

            responseHandler.handleException(e)

        }

    }

}