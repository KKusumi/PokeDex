package com.example.local.db.mapper

import com.example.local.db.entitiy.PokemonDetailEntityImpl
import com.example.pokedex.model.view.PokemonDetailView

internal fun PokemonDetailView.toPokemonDetailEntityImpl() = PokemonDetailEntityImpl(
    id = this.id,
    name = this.name,
    type1 = this.type1.toTypeXEntityImpl(),
    type2 = this.type2?.toTypeXEntityImpl(),
    ability1 = this.ability1.toAbilityXEntityImpl(),
    ability2 = this.ability2?.toAbilityXEntityImpl(),
    hiddenAbility = this.hiddenAbility?.toAbilityXEntityImpl(),
    height = this.height,
    weight = this.weight,
    hp = this.hp,
    attack = this.attack,
    defense = this.defense,
    specialAttack = this.specialAttack,
    specialDefense = this.specialDefense,
    speed = this.speed
)