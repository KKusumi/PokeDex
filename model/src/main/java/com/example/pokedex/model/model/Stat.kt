package com.example.pokedex.model.model

import com.example.response_model.StatResponse

data class Stat(
    val baseStat: Int,
    val effort: Int,
    val stat: StatX
) {

    companion object {
        fun transform(statResponse: StatResponse): Stat {
            return Stat(
                baseStat = statResponse.base_stat,
                effort = statResponse.effort,
                stat = StatX.transform(statResponse.stat)
            )
        }
    }
}