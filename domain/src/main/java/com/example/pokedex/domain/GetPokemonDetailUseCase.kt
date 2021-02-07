package com.example.pokedex.domain

import com.example.pokedex.model.PokeDexException
import com.example.pokedex.model.Pokemon
import com.example.repository.PokemonDetailRepository
import com.github.michaelbull.result.Result

interface GetPokemonDetailUseCase {
    suspend fun execute(id: Int): Result<Pokemon, PokeDexException>
}

internal class GetPokemonDetailUseCaseImpl(
    private val pokemonDetailRepository: PokemonDetailRepository
) : GetPokemonDetailUseCase {

    override suspend fun execute(id: Int): Result<Pokemon, PokeDexException> {
        return pokemonDetailRepository.fetchData(id)
    }
}
