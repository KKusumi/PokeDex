package com.example.pokedex.di

import com.example.pokedex.ui.home.HomeViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory {
        HomeViewModel(get(), get())
    }
}