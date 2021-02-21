package com.example.repository

import com.example.local.db.PokemonDetailDatabase
import com.example.local.db.entitiy.PokemonDetailEntity
import com.example.pokedex.api.client.PokeApiClient
import com.example.pokedex.model.model.EmptyResponseBodyException
import com.example.pokedex.model.model.PokeDexException
import com.example.pokedex.model.view.PokemonDetailView
import com.example.response_model.PokemonDetailResponse
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.flatMap
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface PokemonDetailRepository {
    suspend fun isSaved(id: Int): Boolean
    suspend fun queryData(id: Int): Flow<PokemonDetailView>
    suspend fun fetchData(id: Int): Result<PokemonDetailView, PokeDexException>
}

internal class PokemonDetailRepositoryImpl(
    private val pokeApiClient: PokeApiClient,
    private val pokemonDetailDatabase: PokemonDetailDatabase
) : ApiRepository(), PokemonDetailRepository {

    override suspend fun isSaved(id: Int): Boolean {
        return pokemonDetailDatabase.isSaved(id)
    }

    override suspend fun queryData(id: Int): Flow<PokemonDetailView> {
        return pokemonDetailDatabase.pokemonDetail(id).map {
            it.first().toPokemonDetailView()
        }
    }

    override suspend fun fetchData(id: Int): Result<PokemonDetailView, PokeDexException> {
        return execute { pokeApiClient.fetchPokemonDetail(id) }.flatMap {
            if (it != null) {
                val pokemonDetailView = PokemonDetailView.transform(it)
                pokemonDetailDatabase.savePokemonDetail(pokemonDetailView)
                Ok(pokemonDetailView)
            } else {
                Err(EmptyResponseBodyException())
            }
        }
    }
}

private fun PokemonDetailEntity.toPokemonDetailView() = PokemonDetailView(
    id = this.id,
    name = this.name,
    type1 = this.type1,
    type2 = this.type2,
    ability1 = this.ability1,
    ability2 = this.ability2,
    hiddenAbility = this.hiddenAbility,
    height = this.height,
    weight = this.weight,
    hp = this.hp,
    attack = this.attack,
    defense = this.defense,
    specialAttack = this.specialAttack,
    specialDefense = this.specialDefense,
    speed = this.speed
)