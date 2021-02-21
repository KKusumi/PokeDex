package com.example.pokedex.domain

import com.example.pokedex.model.model.PokeDexException
import com.example.pokedex.model.view.PokemonListView
import com.example.repository.PokemonListRepository
import com.github.michaelbull.result.Result
import kotlinx.coroutines.flow.Flow

interface GetPokemonListUseCase {
    suspend fun pokemonListView(): Flow<PokemonListView>
    suspend fun refresh(): Result<Unit, PokeDexException>
}

internal class GetPokemonListUseCaseImpl(
    private val pokemonListRepository: PokemonListRepository
) : GetPokemonListUseCase {

    override suspend fun pokemonListView() = pokemonListRepository.pokemonListView()

    override suspend fun refresh() = pokemonListRepository.refresh()
}
