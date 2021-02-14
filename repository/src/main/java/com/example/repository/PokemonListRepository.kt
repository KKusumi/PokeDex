package com.example.repository

import com.example.local.db.PokemonListItemDatabase
import com.example.local.db.entitiy.PokemonListItemEntity
import com.example.pokedex.api.client.PokeApiClient
import com.example.pokedex.model.model.EmptyResponseBodyException
import com.example.pokedex.model.model.PokeDexException
import com.example.pokedex.model.view.PokemonListView
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.flatMap
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface PokemonListRepository {
    suspend fun pokemonListView(): Flow<PokemonListView>
    suspend fun refresh(): Result<Unit, PokeDexException>
}

internal class PokemonListRepositoryImpl(
    private val pokeApiClient: PokeApiClient,
    private val pokemonListItemDatabase: PokemonListItemDatabase
) : ApiRepository(), PokemonListRepository {

    override suspend fun pokemonListView(): Flow<PokemonListView> {
        return pokemonListItemDatabase.pokemonListItems().map {
            PokemonListView(
                results = it.map { pokemonListItem ->
                    pokemonListItem.toPokemonListItem()
                }
            )
        }
    }

    override suspend fun refresh(): Result<Unit, PokeDexException> {
        return execute { pokeApiClient.fetchPokemonList() }.flatMap {
            if (it != null) {
                val pokemonListView = PokemonListView.transform(it)
                pokemonListItemDatabase.savePokemonListItems(pokemonListView.results)
                Ok(Unit)
            } else {
                Err(EmptyResponseBodyException())
            }
        }
    }
}

private fun PokemonListItemEntity.toPokemonListItem() =
    PokemonListView.PokemonListItem(
        name = this.name,
        url = this.url
    )