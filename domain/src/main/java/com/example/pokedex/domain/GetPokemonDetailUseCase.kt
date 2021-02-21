package com.example.pokedex.domain

import com.example.pokedex.model.model.PokeDexException
import com.example.pokedex.model.view.PokemonDetailView
import com.example.repository.PokemonDetailRepository
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.coroutines.flow.Flow

interface GetPokemonDetailUseCase {
    suspend fun isSaved(id: Int): Boolean
    suspend fun queryData(id: Int): Flow<PokemonDetailView>
    suspend fun fetchData(id: Int): Result<PokemonDetailView, PokeDexException>
}

internal class GetPokemonDetailUseCaseImpl(
    private val pokemonDetailRepository: PokemonDetailRepository
) : GetPokemonDetailUseCase {

    override suspend fun isSaved(id: Int) = pokemonDetailRepository.isSaved(id)

    override suspend fun queryData(id: Int): Flow<PokemonDetailView> {
        return pokemonDetailRepository.queryData(id)
    }

    override suspend fun fetchData(id: Int) = pokemonDetailRepository.fetchData(id)
}
