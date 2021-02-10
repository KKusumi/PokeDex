package com.example.pokedex.model.model

import com.example.response_model.ItemResponse

data class Item(
    val name: String,
    val url: String
) {

    companion object {
        fun transform(itemResponse: ItemResponse): Item {
            return Item(
                name = itemResponse.name ?: "",
                url = itemResponse.url ?: ""
            )
        }
    }
}