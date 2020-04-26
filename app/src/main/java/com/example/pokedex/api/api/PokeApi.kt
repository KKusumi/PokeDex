package com.example.pokedex.api.api

import com.example.pokedex.api.response.PokemonListResponse
import com.example.pokedex.model.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon")
    suspend fun pokemon(
        @Query("limit") limit: Int
    ): PokemonListResponse

    @GET("pokemon/{id}")
    suspend fun pokemonDetail(@Path("id") id: Int): Pokemon
}