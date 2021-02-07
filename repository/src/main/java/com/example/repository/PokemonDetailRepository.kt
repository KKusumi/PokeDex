package com.example.repository

import com.example.pokedex.api.client.PokeApiClient
import com.example.pokedex.model.EmptyResponseBodyException
import com.example.pokedex.model.PokeDexException
import com.example.pokedex.model.Pokemon
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.flatMap

interface PokemonDetailRepository {
    suspend fun fetchData(id: Int): Result<Pokemon, PokeDexException>
}

internal class PokemonDetailRepositoryImpl(
    private val pokeApiClient: PokeApiClient
) : ApiRepository(), PokemonDetailRepository {

    override suspend fun fetchData(id: Int): Result<Pokemon, PokeDexException> {
        return execute { pokeApiClient.fetchPokemonDetail(id) }.flatMap {
            if (it != null) {
                Ok(it)
            } else {
                Err(EmptyResponseBodyException())
            }
        }
    }
}