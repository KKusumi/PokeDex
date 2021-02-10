package com.example.pokedex.model.model

import com.example.response_model.StatXResponse

data class StatX(
    val name: String,
    val url: String
) {

    companion object {
        fun transform(statXResponse: StatXResponse): StatX{
            return StatX(
                name = statXResponse.name ?: "",
                url = statXResponse.url ?: ""
            )
        }
    }
}