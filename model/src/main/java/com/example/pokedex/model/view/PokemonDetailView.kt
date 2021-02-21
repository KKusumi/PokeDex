package com.example.pokedex.model.view

import com.example.pokedex.model.model.AbilityX
import com.example.pokedex.model.model.TypeX
import com.example.response_model.PokemonDetailResponse

data class PokemonDetailView(
    val id: Int,
    val name: String,
    val type1: String,
    val type2: String?,
    val ability1: String,
    val ability2: String?,
    val hiddenAbility: String?,
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

        val Empty = PokemonDetailView(
            id = 0,
            name = "",
            type1 = "",
            type2 = null,
            ability1 = "",
            ability2 = null,
            hiddenAbility = null,
            height = 0,
            weight = 0,
            hp = 0,
            attack = 0,
            defense = 0,
            specialAttack = 0,
            specialDefense = 0,
            speed = 0
        )

        fun transform(pokemonDetailResponse: PokemonDetailResponse): PokemonDetailView {
            return PokemonDetailView(
                id = pokemonDetailResponse.id,
                name = pokemonDetailResponse.name ?: "",
                type1 = TypeX.transform(requireNotNull(pokemonDetailResponse.types.find { it.slot == 1 }?.type)).name,
                type2 = pokemonDetailResponse.types
                    .find { it.slot == 2 }
                    ?.type
                    ?.let { TypeX.transform(it).name },
                ability1 = AbilityX.transform(pokemonDetailResponse.abilities[0].ability).name,
                ability2 = pokemonDetailResponse.abilities
                    .elementAtOrNull(1)
                    ?.ability
                    ?.let { AbilityX.transform(it).name },
                hiddenAbility = pokemonDetailResponse.abilities
                    .elementAtOrNull(2)
                    ?.ability
                    ?.let { AbilityX.transform(it).name },
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