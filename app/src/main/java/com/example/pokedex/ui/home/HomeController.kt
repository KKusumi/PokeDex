package com.example.pokedex.ui.home

import com.airbnb.epoxy.TypedEpoxyController
import com.example.pokedex.api.response.PokemonListResponse
import com.example.pokedex.api.response.PokemonListResponse.Pokemon
import com.example.pokedex.homePokemonList
import com.example.pokedex.util.ext.addSpace

class HomeController(
    private val onClickItem: (Pokemon) -> Unit
) : TypedEpoxyController<PokemonListResponse>() {

    override fun buildModels(data: PokemonListResponse?) {

        addSpace(0, 24)

        data?.results?.forEach {
            homePokemonList {
                id(modelCountBuiltSoFar)
                pokemon(it)
                onClickItem { _ ->
                    onClickItem.invoke(it)
                }
            }
        }

        addSpace(0, 24)
    }
}