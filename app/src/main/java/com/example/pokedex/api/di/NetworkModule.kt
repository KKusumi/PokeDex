package com.example.pokedex.api.di

import com.example.pokedex.api.client.PokeApiClient
import com.example.pokedex.api.client.PokeApiClientImpl
import com.example.pokedex.api.service.PokeApiService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module { 
    single {
        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single {
        (get() as Retrofit).create(PokeApiService::class.java)
    }
    single {
        PokeApiClientImpl(pokeApiService = get()) as PokeApiClient
    }
}