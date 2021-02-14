package com.example.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.local.db.dao.PokemonListItemDao
import com.example.local.db.entitiy.PokemonListItemEntityImpl

@Database(
    entities = [
        (PokemonListItemEntityImpl::class)
    ],
    version = 1
)
internal abstract class CacheDatabase: RoomDatabase() {
    abstract fun pokemonListItemDao(): PokemonListItemDao
}