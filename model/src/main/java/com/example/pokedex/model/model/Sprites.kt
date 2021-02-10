package com.example.pokedex.model.model

import com.example.response_model.SpritesResponse

data class Sprites(
    val backDefault: String,
    val backFemale: String,
    val backShiny: String,
    val backShinyFemale: String,
    val frontDefault: String,
    val frontFemale: String,
    val frontShiny: String,
    val frontShinyFemale: String
) {
    companion object {
        fun transform(spritesResponse: SpritesResponse): Sprites {
            return Sprites(
                backDefault = spritesResponse.back_default ?: "",
                backFemale = spritesResponse.back_female ?: "",
                backShiny = spritesResponse.back_shiny ?: "",
                backShinyFemale = spritesResponse.back_shiny_female ?: "",
                frontDefault = spritesResponse.front_default ?: "",
                frontFemale = spritesResponse.front_female ?: "",
                frontShiny = spritesResponse.front_shiny ?: "",
                frontShinyFemale = spritesResponse.front_shiny_female ?: ""
            )
        }
    }
}