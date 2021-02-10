package com.example.pokedex.model.model

import com.example.response_model.VersionDetailResponse

data class VersionDetail(
    val rarity: Int,
    val version: VersionX
) {

    companion object {
        fun transform(versionDetailResponse: VersionDetailResponse): VersionDetail {
            return VersionDetail(
                rarity = versionDetailResponse.rarity,
                version = VersionX.transform(versionDetailResponse.version)
            )
        }
    }
}