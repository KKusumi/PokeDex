package com.example.pokedex.model.model

import com.example.response_model.MoveXResponse

data class MoveX(
    val name: String,
    val url: String
) {

    companion object {
        fun transform(moveXResponse: MoveXResponse): MoveX {
            return MoveX(
                name = moveXResponse.name ?: "",
                url = moveXResponse.url ?: ""
            )
        }
    }
}