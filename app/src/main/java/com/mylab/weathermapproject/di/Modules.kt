package com.mylab.weathermapproject.di


import com.mylab.weathermapproject.errorhandling.ResponseHandler
import com.mylab.weathermapproject.network.WeatherApi
import com.mylab.weathermapproject.ui.SharedViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {

    single { SharedViewModel(get(), get()) }

}

val responseHandler = module {

    fun provideResponseHandler(): ResponseHandler {
        return ResponseHandler()
    }

    single { provideResponseHandler() }
}

val apiModule = module {

    fun provideWeatherApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }
    single { provideWeatherApi(get()) }

}


val networkModule = module {

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
    single { provideRetrofit() }
}