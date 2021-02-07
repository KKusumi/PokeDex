package com.example.pokedex.di

import com.example.pokedex.home.HomeViewModel
import com.example.pokemon_detail.PokemonDetailViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory {
        HomeViewModel(get(), get())
    }
    factory {
        PokemonDetailViewModel(get(), get())
    }
}