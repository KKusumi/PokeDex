package com.example.response_model

data class HeldItemResponse(
    val item: ItemResponse,
    val version_details: List<VersionDetailResponse>
)