package com.example.pokedex.api.di

import com.example.pokedex.usecase.PokemonUseCase
import com.example.pokedex.usecase.PokemonUseCaseDebug
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        PokemonUseCaseDebug() as PokemonUseCase
        // PokemonUseCaseImpl(pokeApiClient = get()) as PokemonUseCase
    }
}