package com.example.pokedex.model.model

import com.example.response_model.VersionGroupDetailResponse

data class VersionGroupDetail(
    val levelLearnedAt: Int,
    val moveLearnMethod: MoveLearnMethod,
    val versionGroup: VersionGroup
) {

    companion object {
        fun transform(versionGroupDetailResponse: VersionGroupDetailResponse): VersionGroupDetail {
            return VersionGroupDetail(
                levelLearnedAt = versionGroupDetailResponse.level_learned_at,
                moveLearnMethod = MoveLearnMethod.transform(versionGroupDetailResponse.move_learn_method),
                versionGroup = VersionGroup.transform(versionGroupDetailResponse.version_group)
            )
        }
    }
}