package com.example.pokedex.model.model

import com.example.response_model.VersionGroupResponse

data class VersionGroup(
    val name: String,
    val url: String
) {
    companion object {
        fun transform(versionGroupResponse: VersionGroupResponse): VersionGroup {
            return VersionGroup(
                name = versionGroupResponse.name ?: "",
                url = versionGroupResponse.url ?: ""
            )
        }
    }
}