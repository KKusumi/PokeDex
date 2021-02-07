package com.example.pokedex.di

import com.example.pokedex.domain.GetPokemonDetailUseCase
import com.example.pokedex.domain.GetPokemonDetailUseCaseImpl
import com.example.pokedex.domain.GetPokemonListUseCase
import com.example.pokedex.domain.GetPokemonListUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetPokemonDetailUseCaseImpl(
            pokemonDetailRepository = get()
        ) as GetPokemonDetailUseCase
    }
    factory {
        GetPokemonListUseCaseImpl(
            pokemonListRepository = get()
        ) as GetPokemonListUseCase
    }
}