package com.example.pokemon_detail

import com.airbnb.epoxy.TypedEpoxyController
import com.example.pokedex.model.Pokemon

class PokemonDetailController : TypedEpoxyController<Pokemon>() {

    override fun buildModels(data: Pokemon?) {
        data?.let {
            leftPokemonStatusView {
                id(modelCountBuiltSoFar)
                pokemon(it)
            }
            rightPokemonStatusView {
                id(modelCountBuiltSoFar)
                pokemon(it)
            }
        }
    }
}