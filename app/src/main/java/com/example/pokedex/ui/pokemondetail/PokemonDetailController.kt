package com.example.pokedex.ui.pokemondetail

import com.airbnb.epoxy.TypedEpoxyController
import com.example.pokedex.leftPokemonStatusView
import com.example.pokedex.model.Pokemon
import com.example.pokedex.rightPokemonStatusView

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