package com.example.pokedex.model

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

    fun getTypesText() = if (types.size == 1) {
        types[0].type.name
    } else {
        types[0].type.name + "・" + types[1].type.name
    }

    fun getHp() = stats.find { it.stat.name == "hp" }

    fun getAttack() = stats.find { it.stat.name == "attack" }

    fun getDefence() = stats.find { it.stat.name == "defence" }

    fun getSpecialAttack() = stats.find { it.stat.name == "special-attack" }

    fun getSpecialDefence() = stats.find { it.stat.name == "special-defence" }

    fun getSpeed() = stats.find { it.stat.name == "speed" }
}