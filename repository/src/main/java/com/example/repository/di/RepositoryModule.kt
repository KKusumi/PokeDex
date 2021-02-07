package com.example.repository.di

import com.example.repository.PokemonDetailRepository
import com.example.repository.PokemonDetailRepositoryImpl
import com.example.repository.PokemonListRepository
import com.example.repository.PokemonListRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory {
        PokemonListRepositoryImpl(get()) as PokemonListRepository
    }
    factory {
        PokemonDetailRepositoryImpl(get()) as PokemonDetailRepository
    }
}