package com.example.pokedex.api.client

import com.example.pokedex.api.api.PokeApi
import com.example.pokedex.model.PokeDexApiException
import com.example.pokedex.model.PokeDexApiResponseException
import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.PokemonListResponse
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import retrofit2.Response

interface PokeApiClient {
    companion object {
        private const val TOTAL_POKEMONS_COUNT = 807
    }

    suspend fun fetchPokemonList(limit: Int = TOTAL_POKEMONS_COUNT): Result<PokemonListResponse?, PokeDexApiException>
    suspend fun fetchPokemonDetail(id: Int): Result<Pokemon?, PokeDexApiException>
}

internal class PokeApiClientImpl(
    private val pokeApi: PokeApi
) : PokeApiClient {

    override suspend fun fetchPokemonList(limit: Int): Result<PokemonListResponse?, PokeDexApiException> {
        return execute { pokeApi.pokemon(limit) }
    }

    override suspend fun fetchPokemonDetail(id: Int): Result<Pokemon?, PokeDexApiException> {
        return execute { pokeApi.pokemonDetail(id) }
    }

    private suspend fun <T> execute(block: suspend () -> Response<T>): Result<T?, PokeDexApiException> {
        return kotlin.runCatching {
            block.invoke()
        }.fold(
            onSuccess = {
                if (it.isSuccessful) {
                    Ok(it.body())
                } else {
                    Err(PokeDexApiResponseException())
                }
            },
            onFailure = {
                Err(PokeDexApiException())
            }
        )
    }
}