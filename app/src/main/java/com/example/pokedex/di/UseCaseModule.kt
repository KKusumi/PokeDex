package com.example.pokedex.di

import com.example.pokedex.usecase.GetAllPokemonUseCase
import com.example.pokedex.usecase.GetAllPokemonUseCaseDebug
import com.example.pokedex.usecase.GetPokemonDetailUseCase
import com.example.pokedex.usecase.GetPokemonDetailUseCaseDebug
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetPokemonDetailUseCaseDebug() as GetPokemonDetailUseCase
    }
    factory {
        GetAllPokemonUseCaseDebug() as GetAllPokemonUseCase
    }
}