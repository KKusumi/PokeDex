package com.example.pokedex.api.client

import com.example.pokedex.api.response.PokemonListResponse
import com.example.pokedex.api.api.PokeApi
import com.example.pokedex.model.Pokemon

interface PokeApiClient {
    suspend fun fetchAllPokemon(): PokemonListResponse
    suspend fun fetchPokemonDetail(id: Int): Pokemon
}

class PokeApiClientImpl(
    private val pokeApi: PokeApi
): PokeApiClient {

    override suspend fun fetchAllPokemon(): PokemonListResponse {
        return pokeApi.pokemon()
    }

    override suspend fun fetchPokemonDetail(id: Int): Pokemon {
        return pokeApi.pokemonDetail(id)
    }
}