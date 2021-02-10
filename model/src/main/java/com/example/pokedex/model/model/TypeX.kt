package com.example.pokedex.model.model

import com.example.response_model.TypeXResponse

data class TypeX(
    val name: String,
    val url: String
) {

    val colorCode = when (name) {
        "bug" -> "#90c12c"
        "dark" -> "#5a5366"
        "dragon" -> "#0b6dc4"
        "electric" -> "#f3d23b"
        "fairy" -> "#ec8fe5"
        "fighting" -> "#ce4069"
        "fire" -> "#ff9c54"
        "flying" -> "#8ea8dd"
        "ghost" -> "#5269ac"
        "grass" -> "#62bb5a"
        "ground" -> "#d97747"
        "ice" -> "#73cec0"
        "normal" -> "#9099a0"
        "poison" -> "#ab6ac8"
        "psychic" -> "#f97176"
        "rock" -> "#c7b78b"
        "steel" -> "#5a8ea1"
        "water" -> "#4d90d5"
        else -> "#ffffff"
    }

    companion object {
        fun transform(typeXResponse: TypeXResponse): TypeX {
            return TypeX(
                name = typeXResponse.name ?: "",
                url = typeXResponse.url ?: ""
            )
        }
    }
}