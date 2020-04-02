package com.example.pokedex.ui.home

import com.airbnb.epoxy.TypedEpoxyController
import com.example.pokedex.api.response.PokemonListResponse
import com.example.pokedex.homePokemonList

class HomeController : TypedEpoxyController<PokemonListResponse>() {

    override fun buildModels(data: PokemonListResponse?) {
        data?.results?.forEach { pokemon ->
            homePokemonList {
                id(modelCountBuiltSoFar)
                pokemon(pokemon)
            }
        }
    }
}