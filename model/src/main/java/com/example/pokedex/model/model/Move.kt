package com.example.pokedex.model.model

import com.example.response_model.MoveResponse

data class Move(
    val move: MoveX,
    val versionGroupDetail: List<VersionGroupDetail>
) {
    companion object {
        fun transform(moveResponse: MoveResponse): Move {
            return Move(
                move = MoveX.transform(moveResponse.move),
                versionGroupDetail = moveResponse.version_group_details.map {
                    VersionGroupDetail.transform(
                        it
                    )
                }
            )
        }
    }
}