package com.example.pokedex.domain

import com.example.pokedex.model.PokeDexException
import com.example.pokedex.model.PokemonListResponse
import com.example.repository.PokemonListRepository
import com.github.michaelbull.result.Result

interface GetPokemonListUseCase {
    suspend fun execute(): Result<PokemonListResponse, PokeDexException>
}

class GetPokemonListUseCaseImpl(
    private val pokemonListRepository: PokemonListRepository
) : GetPokemonListUseCase {
    override suspend fun execute(): Result<PokemonListResponse, PokeDexException> {
        return pokemonListRepository.fetchData()
    }
}
