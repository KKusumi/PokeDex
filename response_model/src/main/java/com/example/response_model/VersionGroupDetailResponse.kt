package com.example.response_model

data class VersionGroupDetailResponse(
    val level_learned_at: Int,
    val move_learn_method: MoveLearnMethodResponse,
    val version_group: VersionGroupResponse
)