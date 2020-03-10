package com.example.pokedex.di

import com.example.pokedex.usecase.GetPokemonDetailUseCase
import com.example.pokedex.usecase.GetPokemonDetailUseCaseImpl
import com.example.pokedex.usecase.GetPokemonListUseCase
import com.example.pokedex.usecase.GetPokemonListUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetPokemonDetailUseCaseImpl(get()) as GetPokemonDetailUseCase
    }
    factory {
        GetPokemonListUseCaseImpl(get()) as GetPokemonListUseCase
    }
}