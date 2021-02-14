package com.example.local.db.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey

interface PokemonListItemEntity {
    val name: String
    val url: String
}

@Entity(tableName = "pokemonListItem")
internal data class PokemonListItemEntityImpl(
    @PrimaryKey
    override val name: String,
    override val url: String
): PokemonListItemEntity