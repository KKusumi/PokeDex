package com.example.pokedex.model.model

import com.example.response_model.AbilityXResponse

data class  AbilityX(
    val name: String,
    val url: String
) {
    companion object {
        fun transform(abilityXResponse: AbilityXResponse): AbilityX {
            return AbilityX(
                name = abilityXResponse.name ?: "",
                url = abilityXResponse.url ?: ""
            )
        }
    }
}