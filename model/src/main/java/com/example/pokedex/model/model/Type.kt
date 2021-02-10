package com.example.pokedex.model.model

import com.example.response_model.TypeResponse

data class Type(
    val slot: Int,
    val type: TypeX
) {

    companion object {
        fun transform(typeResponse: TypeResponse): Type {
            return Type(
                slot = typeResponse.slot,
                type = TypeX.transform(typeResponse.type)
            )
        }
    }
}