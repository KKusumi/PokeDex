package com.example.pokedex.usecase

import com.example.pokedex.api.client.PokeApiClient
import com.example.pokedex.api.response.PokemonListResponse
import com.example.pokedex.model.Result

interface GetAllPokemonUseCase {
    suspend fun execute(): Result<PokemonListResponse>
}

class GetAllPokemonUseCaseImpl(private val pokeApiClient: PokeApiClient) : GetAllPokemonUseCase {
    override suspend fun execute(): Result<PokemonListResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class GetAllPokemonUseCaseDebug() : GetAllPokemonUseCase {
    override suspend fun execute() = Result.Success(
        PokemonListResponse(
            count = 0,
            next = "",
            previous = "",
            results = emptyList()
        )
    )
}