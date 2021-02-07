package com.example.pokedex.home

import com.airbnb.epoxy.TypedEpoxyController
import com.example.pokedex.common.space
import com.example.pokedex.model.PokemonListResponse
import com.example.pokedex.model.PokemonListResponse.Pokemon

class HomeController(
    private val onClickItem: (Pokemon) -> Unit
) : TypedEpoxyController<PokemonListResponse>() {

    override fun buildModels(data: PokemonListResponse?) {

        space {
            id(modelCountBuiltSoFar)
            widthDp(0)
            heightDp(24)
        }

        data?.results?.forEach {
            homePokemonList {
                id(modelCountBuiltSoFar)
                pokemon(it)
                onClickItem { _ ->
                    onClickItem.invoke(it)
                }
            }
        }

        space {
            id(modelCountBuiltSoFar)
            widthDp(0)
            heightDp(24)
        }
    }
}