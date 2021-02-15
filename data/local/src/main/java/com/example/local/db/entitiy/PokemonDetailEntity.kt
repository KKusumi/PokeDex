package com.example.local.db.entitiy

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

interface PokemonDetailEntity {
    val id: Int
    val name: String
    val type1: TypeXEntity
    val type2: TypeXEntity?
    val ability1: AbilityXEntity
    val ability2: AbilityXEntity?
    val hiddenAbility: AbilityXEntity?
    val height: Int
    val weight: Int
    val hp: Int
    val attack: Int
    val defense: Int
    val specialAttack: Int
    val specialDefense: Int
    val speed: Int
}

@Entity(tableName = "pokemon_detail")
internal data class PokemonDetailEntityImpl(
    @PrimaryKey
    override val id: Int,
    override val name: String,
    @ColumnInfo(name = "type_1")
    @Embedded override val type1: TypeXEntityImpl,
    @ColumnInfo(name = "type_2")
    @Embedded override val type2: TypeXEntityImpl?,
    @ColumnInfo(name = "ability_1")
    @Embedded override val ability1: AbilityXEntityImpl,
    @ColumnInfo(name = "ability_2")
    @Embedded override val ability2: AbilityXEntityImpl?,
    @ColumnInfo(name = "hidden_ability")
    @Embedded override val hiddenAbility: AbilityXEntity?,
    override val height: Int,
    override val weight: Int,
    override val hp: Int,
    override val attack: Int,
    override val defense: Int,
    @ColumnInfo(name = "special_attack")
    override val specialAttack: Int,
    @ColumnInfo(name = "special_defense")
    override val specialDefense: Int,
    override val speed: Int
): PokemonDetailEntity