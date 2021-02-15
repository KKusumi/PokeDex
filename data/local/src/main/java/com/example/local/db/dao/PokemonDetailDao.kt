package com.example.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.local.db.entitiy.PokemonDetailEntityImpl
import kotlinx.coroutines.flow.Flow

@Dao
internal abstract class PokemonDetailDao {
    @Query("SELECT * FROM pokemon_detail WHERE id = :id")
    abstract fun pokemonDetail(id: Int): Flow<PokemonDetailEntityImpl>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(pokemonDetail: PokemonDetailEntityImpl)

    @Query("DELETE FROM pokemon_detail")
    abstract fun deleteAll()
}