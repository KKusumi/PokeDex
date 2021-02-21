package com.example.pokedex.shared.ext

fun String?.convertToCamelCase() = if (this?.isEmpty() == true) {
    ""
} else {
    this?.first()?.plus(this.substring(1))
}