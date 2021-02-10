package com.example.repository

import com.example.pokedex.api.client.PokeApiClient
import com.example.pokedex.api.response.EmptyResponseBodyException
import com.example.pokedex.api.response.PokeDexException
import com.example.pokedex.model.view.PokemonDetailView
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.flatMap

interface PokemonDetailRepository {
    suspend fun fetchData(id: Int): Result<PokemonDetailView, PokeDexException>
}

internal class PokemonDetailRepositoryImpl(
    private val pokeApiClient: PokeApiClient
) : ApiRepository(), PokemonDetailRepository {

    override suspend fun fetchData(id: Int): Result<PokemonDetailView, PokeDexException> {
        return execute { pokeApiClient.fetchPokemonDetail(id) }.flatMap {
            if (it != null) {
                Ok(PokemonDetailView.transform(it))
            } else {
                Err(EmptyResponseBodyException())
            }
        }
    }
}