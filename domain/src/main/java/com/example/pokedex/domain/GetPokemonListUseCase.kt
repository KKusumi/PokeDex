package com.example.pokedex.domain

import com.example.pokedex.api.response.PokeDexException
import com.example.pokedex.model.view.PokemonListView
import com.example.repository.PokemonListRepository
import com.github.michaelbull.result.Result

interface GetPokemonListUseCase {
    suspend fun execute(): Result<PokemonListView, PokeDexException>
}

class GetPokemonListUseCaseImpl(
    private val pokemonListRepository: PokemonListRepository
) : GetPokemonListUseCase {
    override suspend fun execute(): Result<PokemonListView, PokeDexException> {
        return pokemonListRepository.fetchData()
    }
}
