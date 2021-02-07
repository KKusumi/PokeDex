package com.example.pokedex.model

import java.util.*

data class Pokemon(
    val abilities: List<Ability>,
    val base_experience: Int,
    val forms: List<Form>,
    val game_indices: List<GameIndice>,
    val height: Int,
    val held_items: List<HeldItem>,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val moves: List<Move>,
    val name: String,
    val order: Int,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
) {

    val frontImageUrl
        get() = generatePokemonFrontImageUrl(id.toString())

    val backImageUrl
        get() = generatePokemonBackImageUrl(id.toString())

    fun getCamelCaseName(): String = if (name.isEmpty()) {
        ""
    } else {
        name[0].toUpperCase() + name.substring(1)
    }

    fun getSlot1Type() = this.types.find { it.slot == 1 }?.type?.name

    fun getTypesText() = if (types.size == 1) {
        types[0].type.name.toUpperCase(Locale.getDefault())
    } else {
        types[0].type.name.toUpperCase(Locale.getDefault()) + "・" + types[1].type.name.toUpperCase(Locale.getDefault())
    }

    fun getSlot1AbilityText() = abilities.find { it.slot == 1 }?.ability?.name ?: "なし"

    fun getSlot2AbilityText() = abilities.find { it.slot == 2 }?.ability?.name ?: "なし"

    fun getHideAbilityText() = abilities.find { it.is_hidden }?.ability?.name ?: "なし"

    fun getHp() = stats.find { it.stat.name == "hp" }?.base_stat

    fun getAttack() = stats.find { it.stat.name == "attack" }?.base_stat

    fun getDefense() = stats.find { it.stat.name == "defense" }?.base_stat

    fun getSpecialAttack() = stats.find { it.stat.name == "special-attack" }?.base_stat

    fun getSpecialDefense() = stats.find { it.stat.name == "special-defense" }?.base_stat

    fun getSpeed() = stats.find { it.stat.name == "speed" }?.base_stat

    private fun generatePokemonFrontImageUrl(number: String) =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"

    private fun generatePokemonBackImageUrl(number: String) =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/${number}.png"
}