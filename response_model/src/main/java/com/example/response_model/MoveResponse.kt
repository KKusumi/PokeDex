package com.example.response_model

data class MoveResponse(
    val move: MoveXResponse,
    val version_group_details: List<VersionGroupDetailResponse>
)