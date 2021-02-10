package com.example.pokedex.model.model

import com.example.response_model.VersionXResponse

data class VersionX(
    val name: String,
    val url: String
) {

    companion object {
        fun transform(versionXResponse: VersionXResponse): VersionX {
            return VersionX(
                name = versionXResponse.name ?: "",
                url = versionXResponse.url ?: ""
            )
        }
    }
}