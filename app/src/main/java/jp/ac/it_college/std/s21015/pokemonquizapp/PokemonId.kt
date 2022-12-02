package jp.ac.it_college.std.s21015.pokemonquizapp

data class Pokemon(
    val pokemon: List<PokemonData>
)

data class PokemonData (
    val id: Int,
    val name: String
    )

