package com.mylab.weathermapproject.di

import android.app.Application
import com.mylab.weathermapproject.di.apiModule
import com.mylab.weathermapproject.di.networkModule
import com.mylab.weathermapproject.di.responseHandler
import com.mylab.weathermapproject.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(
                viewModelModule,
                apiModule,
                networkModule,
                responseHandler
            )
        }


    }
}