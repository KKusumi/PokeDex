package com.example.pokedex.model.view

import com.example.pokedex.model.model.Ability
import com.example.pokedex.model.model.Form
import com.example.pokedex.model.model.GameIndice
import com.example.pokedex.model.model.HeldItem
import com.example.pokedex.model.model.Move
import com.example.pokedex.model.model.Species
import com.example.pokedex.model.model.Sprites
import com.example.pokedex.model.model.Stat
import com.example.pokedex.model.model.Type
import com.example.response_model.PokemonDetailResponse
import java.util.*

data class PokemonDetailView(
    val abilities: List<Ability>,
    val baseExperience: Int,
    val forms: List<Form>,
    val gameIndices: List<GameIndice>,
    val height: Int,
    val heldItems: List<HeldItem>,
    val id: Int,
    val isDefault: Boolean,
    val locationAreaEncounters: String,
    val moves: List<Move>,
    val name: String,
    val order: Int,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
) {

    companion object {
        fun transform(pokemonDetailResponse: PokemonDetailResponse): PokemonDetailView {
            return PokemonDetailView(
                abilities = pokemonDetailResponse.abilities.map { Ability.transform(it) },
                baseExperience = pokemonDetailResponse.base_experience,
                forms = pokemonDetailResponse.forms.map { Form.transform(it) },
                gameIndices = pokemonDetailResponse.game_indices.map { GameIndice.transform(it) },
                height = pokemonDetailResponse.height,
                heldItems = pokemonDetailResponse.held_items.map { HeldItem.transform(it) },
                id = pokemonDetailResponse.id,
                isDefault = pokemonDetailResponse.is_default,
                locationAreaEncounters = pokemonDetailResponse.location_area_encounters ?: "",
                moves = pokemonDetailResponse.moves.map { Move.transform(it) },
                name = pokemonDetailResponse.name ?: "",
                order = pokemonDetailResponse.order,
                species = Species.transform(pokemonDetailResponse.species),
                sprites = Sprites.transform(pokemonDetailResponse.sprites),
                stats = pokemonDetailResponse.stats.map { Stat.transform(it) },
                types = pokemonDetailResponse.types.map { Type.transform(it) },
                weight = pokemonDetailResponse.weight
            )
        }
    }

    val imageUrl
        get() = generateImageUrl(id.toString())


    fun getCamelCaseName(): String = if (name.isEmpty()) {
        ""
    } else {
        name[0].toUpperCase() + name.substring(1)
    }

    fun getSlot1Type() = this.types.find { it.slot == 1 }?.type?.name

    fun getTypesText() = if (types.size == 1) {
        types[0].type.name.toUpperCase(Locale.getDefault())
    } else {
        types[0].type.name.toUpperCase(Locale.getDefault()) + "・" + types[1].type.name.toUpperCase(
            Locale.getDefault())
    }

    fun getSlot1AbilityText() = abilities.find { it.slot == 1 }?.ability?.name ?: "なし"

    fun getSlot2AbilityText() = abilities.find { it.slot == 2 }?.ability?.name ?: "なし"

    fun getHideAbilityText() = abilities.find { it.isHidden }?.ability?.name ?: "なし"

    fun getHp() = stats.find { it.stat.name == "hp" }?.baseStat

    fun getAttack() = stats.find { it.stat.name == "attack" }?.baseStat

    fun getDefense() = stats.find { it.stat.name == "defense" }?.baseStat

    fun getSpecialAttack() = stats.find { it.stat.name == "special-attack" }?.baseStat

    fun getSpecialDefense() = stats.find { it.stat.name == "special-defense" }?.baseStat

    fun getSpeed() = stats.find { it.stat.name == "speed" }?.baseStat

    private fun generateImageUrl(number: String) =
        "https://github.com/fanzeyi/pokemon.json/blob/master/images/${number.padStart(3, '0')}.png?raw=true"
}