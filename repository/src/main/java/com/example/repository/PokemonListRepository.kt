package com.example.repository

import com.example.pokedex.api.client.PokeApiClient
import com.example.pokedex.api.response.EmptyResponseBodyException
import com.example.pokedex.api.response.PokeDexException
import com.example.pokedex.model.view.PokemonListView
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.flatMap

interface PokemonListRepository {
    suspend fun fetchData(): Result<PokemonListView, PokeDexException>
}

internal class PokemonListRepositoryImpl(
    private val pokeApiClient: PokeApiClient
) : ApiRepository(), PokemonListRepository {

    override suspend fun fetchData(): Result<PokemonListView, PokeDexException> {
        return execute { pokeApiClient.fetchPokemonList() }.flatMap {
            if (it != null) {
                Ok(PokemonListView.transform(it))
            } else {
                Err(EmptyResponseBodyException())
            }
        }
    }
}