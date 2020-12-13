package com.mylab.weathermapproject.di

import com.mylab.weathermapproject.errorhandling.ResponseHandler
import com.mylab.weathermapproject.network.ApiRepository
import com.mylab.weathermapproject.network.WeatherApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


fun provideApiRepository(
    apiService: WeatherApi,
    responseHandler: ResponseHandler
): ApiRepository {
    return ApiRepository(apiService, responseHandler)
}

fun provideResponseHandler(): ResponseHandler {
    return ResponseHandler()
}

fun provideWeatherApi(retrofit: Retrofit): WeatherApi {
    return retrofit.create(WeatherApi::class.java)
}

fun provideOkHttp(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
}

fun provideCallAdapter(): RxJava3CallAdapterFactory {
    return RxJava3CallAdapterFactory.create()
}

fun provideRetrofit(): Retrofit {

    return Retrofit.Builder()
        .baseUrl(WeatherApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(provideCallAdapter())
        .client(provideOkHttp())
        .build()
}