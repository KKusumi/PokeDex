package com.example.pokemon_detail

import com.airbnb.epoxy.TypedEpoxyController
import com.example.pokedex.model.view.PokemonDetailView

class PokemonDetailController : TypedEpoxyController<PokemonDetailView>() {

    override fun buildModels(data: PokemonDetailView?) {
        data?.let {
            leftPokemonStatusView {
                id(modelCountBuiltSoFar)
                pokemonDetailView(it)
            }
            rightPokemonStatusView {
                id(modelCountBuiltSoFar)
                pokemonDetailView(it)
            }
        }
    }
}