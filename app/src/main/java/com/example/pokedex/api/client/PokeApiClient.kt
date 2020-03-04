package com.example.pokedex.api.client

import com.example.pokedex.api.response.PokemonListResponse
import com.example.pokedex.api.api.PokeApi
import com.example.pokedex.model.Pokemon

interface PokeApiClient {

    suspend fun pokemon(): PokemonListResponse
    suspend fun pokemonDetail(id: Int): Pokemon
}

class PokeApiClientImpl(
    private val pokeApi: PokeApi
): PokeApiClient {

    override suspend fun pokemon(): PokemonListResponse {
        return pokeApi.pokemon()
    }

    override suspend fun pokemonDetail(id: Int): Pokemon {
        return pokeApi.pokemonDetail(id)
    }
}