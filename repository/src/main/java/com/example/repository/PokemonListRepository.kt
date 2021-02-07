package com.example.repository

import com.example.pokedex.api.client.PokeApiClient
import com.example.pokedex.model.EmptyResponseBodyException
import com.example.pokedex.model.PokeDexException
import com.example.pokedex.model.PokemonListResponse
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.flatMap

interface PokemonListRepository {
    suspend fun fetchData(): Result<PokemonListResponse, PokeDexException>
}

internal class PokemonListRepositoryImpl(
    private val pokeApiClient: PokeApiClient
): ApiRepository(), PokemonListRepository {
    override suspend fun fetchData(): Result<PokemonListResponse, PokeDexException> {
        return execute { pokeApiClient.fetchPokemonList() }.flatMap {
            if (it != null) {
                Ok(it)
            } else {
                Err(EmptyResponseBodyException())
            }
        }
    }
}