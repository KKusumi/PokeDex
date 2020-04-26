package com.example.pokedex.api.response

import com.example.pokedex.util.ext.generatePokemonBackImageUrl
import com.example.pokedex.util.ext.generatePokemonFrontImageUrl
import com.example.pokedex.util.ext.generatePokemonNumber

data class PokemonListResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Pokemon>
) {
    data class Pokemon(
        val name: String,
        val url: String
    ) {
        val number
            get() = generatePokemonNumber(url)

        val frontImageUrl
            get() = generatePokemonFrontImageUrl(number.toString())

        val backImageUrl
            get() = generatePokemonBackImageUrl(number.toString())

        fun getCamelCaseName(): String = if (name.isEmpty()) {
            ""
        } else {
            name[0].toUpperCase() + name.substring(1)
        }
    }
}
