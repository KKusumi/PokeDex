package com.example.pokedex.api.client

import com.example.pokedex.api.api.PokeApi
import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.PokemonListResponse

interface PokeApiClient {
    companion object {
        private const val TOTAL_POKEMONS_COUNT = 807
    }
    suspend fun fetchPokemonList(limit: Int = TOTAL_POKEMONS_COUNT): PokemonListResponse
    suspend fun fetchPokemonDetail(id: Int): Pokemon
}

class PokeApiClientImpl(
    private val pokeApi: PokeApi
) : PokeApiClient {

    override suspend fun fetchPokemonList(limit: Int): PokemonListResponse {
        return pokeApi.pokemon(limit)
    }

    override suspend fun fetchPokemonDetail(id: Int): Pokemon {
        return pokeApi.pokemonDetail(id)
    }
}