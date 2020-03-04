package com.example.pokedex.usecase

import com.example.pokedex.api.client.PokeApiClient
import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.Result
import com.example.pokedex.model.Species
import com.example.pokedex.model.Sprites

interface GetPokemonDetailUseCase {
    suspend fun getPokemonDetail(): Result<Pokemon>
}

class GetPokemonDetailUseCaseImpl(
    private val pokeApiClient: PokeApiClient
) : GetPokemonDetailUseCase {

    override suspend fun getPokemonDetail(): Result<Pokemon> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class GetPokemonDetailUseCaseDebug : GetPokemonDetailUseCase {
    override suspend fun getPokemonDetail() = Result.Success(
        Pokemon(
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
    )
}