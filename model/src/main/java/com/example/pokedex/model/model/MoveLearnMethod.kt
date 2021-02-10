package com.example.pokedex.model.model

import com.example.response_model.MoveLearnMethodResponse

data class MoveLearnMethod(
    val name: String,
    val url: String
) {

    companion object {
        fun transform(moveLearnMethodResponse: MoveLearnMethodResponse): MoveLearnMethod {
            return MoveLearnMethod(
                name = moveLearnMethodResponse.name ?: "",
                url = moveLearnMethodResponse.url ?: ""
            )
        }
    }
}