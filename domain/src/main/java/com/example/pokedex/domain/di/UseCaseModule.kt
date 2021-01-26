package com.example.pokedex.di

import com.example.pokedex.usecase.GetPokemonDetailUseCase
import com.example.pokedex.usecase.GetPokemonDetailUseCaseImpl
import com.example.pokedex.domain.GetPokemonListUseCase
import com.example.pokedex.domain.GetPokemonListUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetPokemonDetailUseCaseImpl(get()) as GetPokemonDetailUseCase
    }
    factory {
        GetPokemonListUseCaseImpl(get()) as GetPokemonListUseCase
    }
}