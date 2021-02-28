package com.example.pokedex.shared.ext

fun String?.toCamelCase(): String = if (this?.isNotEmpty() == true) {
    this.first().toUpperCase() + this.substring(1)
} else {
    ""
}