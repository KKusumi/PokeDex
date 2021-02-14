package com.example.local.db

import androidx.room.withTransaction
import com.example.local.db.entitiy.PokemonListItemEntity
import com.example.pokedex.model.view.PokemonListView.PokemonListItem
import kotlinx.coroutines.flow.Flow

interface PokemonListItemDatabase {
    fun pokemonListItems(): Flow<List<PokemonListItemEntity>>
    suspend fun savePokemonListItems(pokemonListItems: List<PokemonListItem>)
}

internal class RoomDatabase(
    private val cacheDatabase: CacheDatabase
) : PokemonListItemDatabase {

    override fun pokemonListItems(): Flow<List<PokemonListItemEntity>> =
        cacheDatabase.pokemonListItemDao().allPokemonListItem()

    override suspend fun savePokemonListItems(pokemonListItems: List<PokemonListItem>) {
        cacheDatabase.withTransaction {
            cacheDatabase.pokemonListItemDao().deleteAll()
            cacheDatabase.pokemonListItemDao().insert(
                pokemonListItems.toPokemonListItemEntities()
            )
        }
    }
}