package com.example.pokedex.usecase

import com.example.pokedex.api.client.PokeApiClient
import com.example.pokedex.api.response.PokemonListResponse
import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.Species
import com.example.pokedex.model.Sprites

interface PokemonUseCase {
    suspend fun getPokemonList(): PokemonListResponse
    suspend fun getPokemonDetail(): Pokemon
}

class PokemonUseCaseImpl(
    private val pokeApiClient: PokeApiClient
) : PokemonUseCase {
    override suspend fun getPokemonList(): PokemonListResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getPokemonDetail(): Pokemon {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class PokemonUseCaseDebug : PokemonUseCase {
    override suspend fun getPokemonList() = PokemonListResponse(
        count = 0,
        next = "",
        previous = "",
        results = emptyList()
    )

    override suspend fun getPokemonDetail() = Pokemon(
        abilities = emptyList(),
        base_experience = 0,
        forms = emptyList(),
        game_indices = emptyList(),
        height = 0,
        held_items = emptyList(),
        id = 0,
        is_default = false,
        location_area_encounters = "",
        moves = emptyList(),
        name = "",
        order = 0,
        species = Species(name = "", url = ""),
        sprites = Sprites(
            back_default = "",
            back_female = "",
            back_shiny = "",
            back_shiny_female = "",
            front_default = "",
            front_female = "",
            front_shiny = "",
            front_shiny_female = ""
        ),
        stats = emptyList(),
        types = emptyList(),
        weight = 0
    )
}