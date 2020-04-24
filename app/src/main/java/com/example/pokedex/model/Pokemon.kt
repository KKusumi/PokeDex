package com.example.pokedex.model

import com.example.pokedex.R
import com.example.pokedex.util.ext.generatePokemonBackImageUrl
import com.example.pokedex.util.ext.generatePokemonFrontImageUrl

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

    fun getTypesText() = if (types.size == 1) {
        types[0].type.name
    } else {
        types[0].type.name + "・" + types[1].type.name
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

    fun getTypeColorHalfCircle(): Int {
        return when (this.types.find { it.slot == 1 }?.type?.name) {
            "normal" ->  R.drawable.ic_normal_color_half_circle
            "fire" ->  R.drawable.ic_fire_color_half_circle
            "water" -> R.drawable.ic_water_color_half_circle
            "grass" -> R.drawable.ic_grass_color_half_circle
            "electric" -> R.drawable.ic_electric_color_half_circle
            "ice" -> R.drawable.ic_ice_color_half_circle
            "fighting" -> R.drawable.ic_fighting_color_half_circle
            "poison" -> R.drawable.ic_poison_color_half_circle
            "ground" -> R.drawable.ic_ground_color_half_circle
            "flying" -> R.drawable.ic_flying_color_half_circle
            "psychic" -> R.drawable.ic_psychic_color_half_circle
            "bug" -> R.drawable.ic_bug_color_half_circle
            "rock" -> R.drawable.ic_rock_color_half_circle
            "ghost" -> R.drawable.ic_ghost_color_half_circle
            "dragon" -> R.drawable.ic_dragon_color_half_circle
            "dark" -> R.drawable.ic_dark_color_half_circle
            "steel" -> R.drawable.ic_steel_color_half_circle
            "fairy" -> R.drawable.ic_fairy_color_half_circle
            else -> R.drawable.ic_normal_color_half_circle
        }
    }
}