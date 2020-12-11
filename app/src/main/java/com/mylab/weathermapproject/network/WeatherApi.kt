package com.mylab.weathermapproject.network

import com.mylab.weathermapproject.datamodel.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    companion object{
        const val BASE_URL = "http://api.openweathermap.org/"
        const val API_KEY = "8aab09f629a3ac73bcedf130a363335a"
    }

    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("lat")latitude:Double,
        @Query("lon")longitude:Double,
        @Query("appid")apiKey:String
    ): WeatherResponse
}