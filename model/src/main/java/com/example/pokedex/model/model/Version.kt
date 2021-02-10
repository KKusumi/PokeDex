package com.example.pokedex.model.model

import com.example.response_model.VersionResponse

data class Version(
    val name: String,
    val url: String
) {

    companion object {
        fun transform(versionResponse: VersionResponse): Version {
            return Version(
                name = versionResponse.name ?: "",
                url = versionResponse.url ?: ""
            )
        }
    }
}