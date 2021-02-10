package com.example.pokedex.model.model

import com.example.response_model.FormResponse

data class Form(
    val name: String,
    val url: String
) {
    companion object {
        fun transform(formResponse: FormResponse): Form {
            return Form(
                name = formResponse.name ?: "",
                url = formResponse.url ?: ""
            )
        }
    }
}