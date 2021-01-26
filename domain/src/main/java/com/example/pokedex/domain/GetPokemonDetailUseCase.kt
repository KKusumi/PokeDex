package com.example.pokedex.usecase

import com.example.pokedex.api.client.PokeApiClient
import com.example.pokedex.model.PokeDexError
import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.Result
import java.net.SocketTimeoutException
import java.net.UnknownHostException

interface GetPokemonDetailUseCase {
    suspend fun execute(id: Int): Result<Pokemon>
}

class GetPokemonDetailUseCaseImpl(
    private val pokeApiClient: PokeApiClient
) : GetPokemonDetailUseCase {

    override suspend fun execute(id: Int): Result<Pokemon> {
        kotlin.runCatching {
            pokeApiClient.fetchPokemonDetail(id)
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
