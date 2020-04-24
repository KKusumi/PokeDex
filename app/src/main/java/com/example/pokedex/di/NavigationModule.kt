package com.example.pokedex.di

import androidx.navigation.NavController
import com.example.pokedex.navigator.HomeNavigator
import org.koin.dsl.module

val navigationModule = module {
    single { (navController: NavController) ->
        HomeNavigator(navController)
    }
}