package com.example.pokedex.api.service

import com.example.pokedex.api.response.PokemonListResponse
import com.example.pokedex.model.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService {

    @GET("/pokemon")
    suspend fun pokemon(): PokemonListResponse

    @GET("/pokemon/{id}")
    suspend fun pokemonDetail(@Path("id") id: Int): Pokemon
}