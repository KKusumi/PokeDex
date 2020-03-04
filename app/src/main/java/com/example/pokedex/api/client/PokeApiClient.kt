package com.example.pokedex.api.client

import com.example.pokedex.api.response.PokemonListResponse
import com.example.pokedex.api.service.PokeApiService
import com.example.pokedex.model.Pokemon

interface PokeApiClient {

    suspend fun pokemon(): PokemonListResponse
    suspend fun pokemonDetail(id: Int): Pokemon
}

class PokeApiClientImpl(
    private val pokeApiService: PokeApiService
): PokeApiClient {

    override suspend fun pokemon(): PokemonListResponse {
        return pokeApiService.pokemon()
    }

    override suspend fun pokemonDetail(id: Int): Pokemon {
        return pokeApiService.pokemonDetail(id)
    }
}