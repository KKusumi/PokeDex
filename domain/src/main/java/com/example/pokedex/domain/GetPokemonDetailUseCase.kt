package com.example.pokedex.domain

import com.example.pokedex.api.response.PokeDexException
import com.example.pokedex.model.view.PokemonDetailView
import com.example.repository.PokemonDetailRepository
import com.github.michaelbull.result.Result

interface GetPokemonDetailUseCase {
    suspend fun execute(id: Int): Result<PokemonDetailView, PokeDexException>
}

internal class GetPokemonDetailUseCaseImpl(
    private val pokemonDetailRepository: PokemonDetailRepository
) : GetPokemonDetailUseCase {

    override suspend fun execute(id: Int): Result<PokemonDetailView, PokeDexException> {
        return pokemonDetailRepository.fetchData(id)
    }
}
