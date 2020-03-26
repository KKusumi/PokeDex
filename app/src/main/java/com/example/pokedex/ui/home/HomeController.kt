package com.example.pokedex.ui.home

import com.airbnb.epoxy.TypedEpoxyController
import com.example.pokedex.api.response.PokemonListResponse

class HomeController: TypedEpoxyController<PokemonListResponse>() {

    override fun buildModels(data: PokemonListResponse?) {
        data?.results?.forEach {
            // リストのアイテムのビューを生成する。
        }
    }
}