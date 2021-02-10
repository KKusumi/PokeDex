package com.example.pokedex.home

import com.airbnb.epoxy.TypedEpoxyController
import com.example.pokedex.common.space
import com.example.pokedex.model.view.PokemonListView

class HomeController(
    private val onClickItem: (Int) -> Unit
) : TypedEpoxyController<PokemonListView>() {

    override fun buildModels(data: PokemonListView?) {

        space {
            id(modelCountBuiltSoFar)
            widthDp(0)
            heightDp(24)
        }

        data?.results?.forEach {
            homePokemonList {
                id(modelCountBuiltSoFar)
                pokemonListItem(it)
                onClickItem { _ ->
                    onClickItem.invoke(it.number)
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