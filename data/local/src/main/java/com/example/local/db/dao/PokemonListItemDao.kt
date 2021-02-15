package com.example.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.local.db.entitiy.PokemonListItemEntityImpl
import kotlinx.coroutines.flow.Flow

@Dao
internal abstract class PokemonListItemDao {
    @Query("SELECT * FROM pokemon_list_item")
    abstract fun allPokemonListItem(): Flow<List<PokemonListItemEntityImpl>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(pokemonListItem: List<PokemonListItemEntityImpl>)

    @Query("DELETE FROM pokemon_list_item")
    abstract fun deleteAll()
}