package com.example.pokedex.usecase

import com.example.pokedex.api.client.PokeApiClient
import com.example.pokedex.api.response.PokemonListResponse
import com.example.pokedex.model.PokeDexError
import com.example.pokedex.model.Result
import java.net.SocketTimeoutException
import java.net.UnknownHostException

interface GetPokemonListUseCase {
    suspend fun execute(): Result<PokemonListResponse>
}

class GetPokemonListUseCaseImpl(private val pokeApiClient: PokeApiClient) : GetPokemonListUseCase {
    override suspend fun execute(): Result<PokemonListResponse> {
        kotlin.runCatching {
            pokeApiClient.fetchAllPokemon()
        }.fold(
            onSuccess = {
                return Result.Success(it)
            },
            onFailure = {
                return when (it) {
                    is SocketTimeoutException, is UnknownHostException -> {
                        Result.Error(PokeDexError.NetworkError())
                    }
                    else -> {
                        Result.Error(PokeDexError.UndefinedError())
                    }
                }
            }
        )
    }
}

class GetPokemonListUseCaseDebug() : GetPokemonListUseCase {
    override suspend fun execute() = Result.Success(
        PokemonListResponse(
            count = 0,
            next = "",
            previous = "",
            results = emptyList()
        )
    )
}