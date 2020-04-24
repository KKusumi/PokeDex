package com.example.pokedex.di

import com.example.pokedex.ui.home.HomeViewModel
import com.example.pokedex.ui.pokemondetail.PokemonDetailViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory {
        HomeViewModel(get(), get())
    }
    factory {
        PokemonDetailViewModel(get(), get())
    }
}