package com.example.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.local.db.dao.PokemonDetailDao
import com.example.local.db.dao.PokemonListItemDao
import com.example.local.db.entitiy.PokemonDetailEntityImpl
import com.example.local.db.entitiy.PokemonListItemEntityImpl

@Database(
    entities = [
        (PokemonListItemEntityImpl::class),
        (PokemonDetailEntityImpl::class)
    ],
    version = 2,
    exportSchema = false
)
internal abstract class CacheDatabase : RoomDatabase() {
    abstract fun pokemonListItemDao(): PokemonListItemDao
    abstract fun pokemonDetailDao(): PokemonDetailDao
}