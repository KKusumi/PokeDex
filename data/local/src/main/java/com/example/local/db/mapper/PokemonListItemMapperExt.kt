package com.example.local.db

import com.example.local.db.entitiy.PokemonListItemEntityImpl
import com.example.pokedex.model.view.PokemonListView.PokemonListItem

internal fun List<PokemonListItem>.toPokemonListItemEntities(): List<PokemonListItemEntityImpl> =
    map { it.toPokemonListItemEntityImpl() }

internal fun PokemonListItem.toPokemonListItemEntityImpl(): PokemonListItemEntityImpl =
    PokemonListItemEntityImpl(
        name = this.name,
        url = this.url
    )