package com.example.pokedex.di

import androidx.navigation.NavController
import com.example.pokedex.common.navigator.HomeNavigator
import com.example.pokedex.common.navigator.PokemonDetailNavigator
import org.koin.dsl.module

val navigationModule = module {
    single { (navController: NavController) ->
        HomeNavigator(navController)
    }
    single { (navController: NavController) ->
        PokemonDetailNavigator(navController)
    }
}