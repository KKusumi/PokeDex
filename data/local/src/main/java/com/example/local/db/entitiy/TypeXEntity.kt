package com.example.local.db.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey

interface TypeXEntity {
    val name: String
    val url: String
}

@Entity(tableName = "type_x")
internal data class TypeXEntityImpl(
    @PrimaryKey
    override val name: String,
    override val url: String
): TypeXEntity