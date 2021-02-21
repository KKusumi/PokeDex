package com.example.local.db.entitiy

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

interface PokemonDetailEntity {
    val id: Int
    val name: String
    val type1: String
    val type2: String?
    val ability1: String
    val ability2: String?
    val hiddenAbility: String?
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
    override val type1: String,
    @ColumnInfo(name = "type_2")
    override val type2: String?,
    @ColumnInfo(name = "ability_1")
    override val ability1: String,
    @ColumnInfo(name = "ability_2")
    override val ability2: String?,
    @ColumnInfo(name = "hidden_ability")
    override val hiddenAbility: String?,
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
) : PokemonDetailEntity