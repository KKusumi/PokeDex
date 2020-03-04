package com.example.pokedex

import android.app.Application
import com.example.pokedex.api.di.networkModule
import com.example.pokedex.api.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    networkModule, useCaseModule
                )
            )
        }
    }
}