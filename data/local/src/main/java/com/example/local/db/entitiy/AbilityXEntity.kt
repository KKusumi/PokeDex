package com.example.local.db.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey

interface AbilityXEntity {
    val name: String
    val url: String
}

@Entity(tableName = "ability_x")
internal data class AbilityXEntityImpl(
    @PrimaryKey
    override val name: String,
    override val url: String
): AbilityXEntity