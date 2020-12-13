package com.mylab.weathermapproject.di


import android.app.Application
import android.app.Service
import com.mylab.weathermapproject.errorhandling.ResponseHandler
import com.mylab.weathermapproject.network.ApiRepository
import com.mylab.weathermapproject.network.WeatherApi
import com.mylab.weathermapproject.ui.MapsViewModel
import com.mylab.weathermapproject.ui.ResultViewModel
import com.mylab.weathermapproject.ui.SharedViewModel
import com.mylab.weathermapproject.ui.SplashViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {

    single { SharedViewModel(get()) }


    viewModel { SplashViewModel(androidApplication()) }
    viewModel { MapsViewModel(androidApplication()) }
    viewModel { ResultViewModel() }

}

val applicationModule = module {

    factory { provideApiRepository(get(), get()) }
    single { provideResponseHandler() }
    single { provideWeatherApi(get()) }
    single { provideRetrofit() }


}

/*val apiRepository = module {

    *//*fun provideApiRepository(
        apiService: WeatherApi,
        responseHandler: ResponseHandler
    ): ApiRepository {
        return ApiRepository(apiService, responseHandler)
    }*//*


}


val responseHandler = module {

    *//*fun provideResponseHandler(): ResponseHandler {
        return ResponseHandler()
    }*//*


}

val apiModule = module {

    *//*fun provideWeatherApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }*//*


}


val networkModule = module {

    *//*fun provideOkHttp(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }*//*

    *//*fun provideCallAdapter(): RxJava3CallAdapterFactory {
        return RxJava3CallAdapterFactory.create()
    }*//*


    *//*fun provideRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(WeatherApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(provideCallAdapter())
            .client(provideOkHttp())
            .build()
    }*//*

}*/

