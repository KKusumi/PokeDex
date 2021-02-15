package com.example.pokedex.model.view

import com.example.pokedex.model.model.AbilityX
import com.example.pokedex.model.model.TypeX
import com.example.response_model.PokemonDetailResponse

data class PokemonDetailView(
    val id: Int,
    val name: String,
    val type1: TypeX,
    val type2: TypeX?,
    val ability1: AbilityX,
    val ability2: AbilityX?,
    val hiddenAbility: AbilityX?,
    val height: Int,
    val weight: Int,
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val specialAttack: Int,
    val specialDefense: Int,
    val speed: Int
) {

    companion object {
        fun transform(pokemonDetailResponse: PokemonDetailResponse): PokemonDetailView {
            return PokemonDetailView(
                id = pokemonDetailResponse.id,
                name = pokemonDetailResponse.name ?: "",
                type1 = TypeX.transform(requireNotNull(pokemonDetailResponse.types.find { it.slot == 1 }?.type)),
                type2 = pokemonDetailResponse.types
                    .find { it.slot == 2 }
                    ?.type
                    ?.let { TypeX.transform(it) },
                ability1 = AbilityX.transform(pokemonDetailResponse.abilities[0].ability),
                ability2 = pokemonDetailResponse.abilities
                    .elementAtOrNull(1)
                    ?.ability
                    ?.let { AbilityX.transform(it) },
                hiddenAbility = pokemonDetailResponse.abilities
                    .elementAtOrNull(2)
                    ?.ability
                    ?.let { AbilityX.transform(it) },
                height = pokemonDetailResponse.height,
                weight = pokemonDetailResponse.weight,
                hp = pokemonDetailResponse.stats.find { it.stat.name == "hp" }?.base_stat ?: 0,
                attack = pokemonDetailResponse.stats.find { it.stat.name == "attack" }?.base_stat
                    ?: 0,
                defense = pokemonDetailResponse.stats.find { it.stat.name == "defense" }?.base_stat
                    ?: 0,
                specialAttack = pokemonDetailResponse.stats.find { it.stat.name == "special-attack" }?.base_stat
                    ?: 0,
                specialDefense = pokemonDetailResponse.stats.find { it.stat.name == "special-defense" }?.base_stat
                    ?: 0,
                speed = pokemonDetailResponse.stats.find { it.stat.name == "speed" }?.base_stat
                    ?: 0,
            )
        }
    }

    val imageUrl get() = generateImageUrl(id.toString())

    private fun generateImageUrl(number: String) =
        "https://github.com/fanzeyi/pokemon.json/blob/master/images/${
            number.padStart(
                3,
                '0'
            )
        }.png?raw=true"
}