package com.example.local.db

import androidx.room.withTransaction
import com.example.local.db.entitiy.PokemonDetailEntity
import com.example.local.db.entitiy.PokemonListItemEntity
import com.example.local.db.mapper.toPokemonDetailEntityImpl
import com.example.local.db.mapper.toPokemonListItemEntities
import com.example.pokedex.model.view.PokemonDetailView
import com.example.pokedex.model.view.PokemonListView.PokemonListItem
import kotlinx.coroutines.flow.Flow

interface PokemonListItemDatabase {
    suspend fun pokemonListItems(): Flow<List<PokemonListItemEntity>>
    suspend fun savePokemonListItems(pokemonListItems: List<PokemonListItem>)
}

interface PokemonDetailDatabase {
    suspend fun isSaved(id: Int): Boolean
    fun pokemonDetail(id: Int): Flow<List<PokemonDetailEntity>>
    suspend fun savePokemonDetail(pokemonDetail: PokemonDetailView)
}

internal class RoomDatabase(
    private val cacheDatabase: CacheDatabase
) : PokemonListItemDatabase, PokemonDetailDatabase {

    override suspend fun isSaved(id: Int): Boolean {
        return cacheDatabase.pokemonDetailDao().isSaved(id)
    }

    override suspend fun pokemonListItems() =
        cacheDatabase.pokemonListItemDao().allPokemonListItem()

    override suspend fun savePokemonListItems(pokemonListItems: List<PokemonListItem>) {
        cacheDatabase.withTransaction {
            cacheDatabase.pokemonListItemDao().deleteAll()
            cacheDatabase.pokemonListItemDao().insert(
                pokemonListItems.toPokemonListItemEntities()
            )
        }
    }

    override fun pokemonDetail(id: Int) = cacheDatabase.pokemonDetailDao().pokemonDetail(id)

    override suspend fun savePokemonDetail(pokemonDetail: PokemonDetailView) {
        cacheDatabase.withTransaction {
            cacheDatabase.pokemonDetailDao().insert(
                pokemonDetail.toPokemonDetailEntityImpl()
            )
        }
    }
}