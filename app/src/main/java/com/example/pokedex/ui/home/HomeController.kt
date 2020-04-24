package com.example.pokedex.ui.home

import com.airbnb.epoxy.TypedEpoxyController
import com.example.pokedex.api.response.PokemonListResponse
import com.example.pokedex.api.response.PokemonListResponse.Pokemon
import com.example.pokedex.homePokemonList

class HomeController(
    private val onClickItem: (Pokemon) -> Unit
) : TypedEpoxyController<PokemonListResponse>() {

    override fun buildModels(data: PokemonListResponse?) {
        data?.results?.forEach {
            homePokemonList {
                id(modelCountBuiltSoFar)
                pokemon(it)
                onClickItem { _ ->
                    onClickItem.invoke(it)
                }
            }
        }
    }
}