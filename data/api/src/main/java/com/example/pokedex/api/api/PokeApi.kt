package com.example.pokedex.api.api

import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon")
    suspend fun pokemon(@Query("limit") limit: Int): Response<PokemonListResponse>

    @GET("pokemon/{id}")
    suspend fun pokemonDetail(@Path("id") id: Int): Response<Pokemon>
}