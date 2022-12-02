package jp.ac.it_college.std.s21015.pokemonquizapp

data class FilteredPokedex(
    val pokedex: List<Dex>
)

data class Dex(
    val id: Int,
    val name: String,
    val entries: List<PokemonId>
)

data class PokemonId(
    val entry_number: Int,
    val pokemon_id: Int
)