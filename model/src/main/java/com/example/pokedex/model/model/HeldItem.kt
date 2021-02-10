package com.example.pokedex.model.model

import com.example.response_model.HeldItemResponse

data class HeldItem(
    val item: Item,
    val versionDetails: List<VersionDetail>
) {
    companion object {
        fun transform(heldItemResponse: HeldItemResponse): HeldItem {
            return HeldItem(
                item = Item.transform(heldItemResponse.item),
                versionDetails = heldItemResponse.version_details.map { VersionDetail.transform(it) }
            )
        }
    }
}