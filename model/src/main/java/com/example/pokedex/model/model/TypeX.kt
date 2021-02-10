package com.example.pokedex.model.model

import com.example.response_model.TypeXResponse

data class TypeX(
    val name: String,
    val url: String
) {

    companion object {
        fun transform(typeXResponse: TypeXResponse): TypeX {
            return TypeX(
                name = typeXResponse.name ?: "",
                url = typeXResponse.url ?: ""
            )
        }
    }
}