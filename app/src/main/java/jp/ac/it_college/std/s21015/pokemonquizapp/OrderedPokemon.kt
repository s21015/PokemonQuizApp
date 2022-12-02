package jp.ac.it_college.std.s21015.pokemonquizapp

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OrderedPokemon(
    val id: String,
    val name: String
)
