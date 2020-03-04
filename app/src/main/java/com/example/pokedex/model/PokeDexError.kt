package com.example.pokedex.model

sealed class PokeDexError(
    val alertTitle: String = "エラーが発生しました。",
    val alertMessage: String = ""
) : Throwable() {
    /**
     * ここにエラーのパターンをクラスとして追加していく。
     * エラーごとにメッセージだけ変える。
     */
    class NetworkError : PokeDexError(alertMessage = "通信状況を確認し、再度お試しください。")
}