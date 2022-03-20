package com.vinicius.androidexercises

import android.app.Application
import com.vinicius.androidexercises.di.networkModule
import com.vinicius.androidexercises.di.repositoryModule
import com.vinicius.androidexercises.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Exercises : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Exercises)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }

}