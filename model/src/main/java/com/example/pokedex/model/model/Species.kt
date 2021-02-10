package com.example.pokedex.model.model

import com.example.response_model.SpeciesResponse

data class Species(
    val name: String,
    val url: String
) {
    companion object {
        fun transform(speciesResponse: SpeciesResponse): Species {
            return Species(
                name = speciesResponse.name ?: "",
                url = speciesResponse.url ?: ""
            )
        }
    }
}