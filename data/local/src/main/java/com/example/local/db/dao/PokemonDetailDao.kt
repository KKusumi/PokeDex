package com.example.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.local.db.entitiy.PokemonDetailEntityImpl
import kotlinx.coroutines.flow.Flow

@Dao
internal abstract class PokemonDetailDao {
    @Query("SELECT EXISTS(SELECT * FROM pokemon_detail WHERE id = :id)")
    abstract suspend fun isSaved(id: Int): Boolean

    @Query("SELECT * FROM pokemon_detail WHERE id = :id")
    abstract fun pokemonDetail(id: Int): Flow<List<PokemonDetailEntityImpl>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(pokemonDetail: PokemonDetailEntityImpl)

    @Query("DELETE FROM pokemon_detail")
    abstract suspend fun deleteAll()
}