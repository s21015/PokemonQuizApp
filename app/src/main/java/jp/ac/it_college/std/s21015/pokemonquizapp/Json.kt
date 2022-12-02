package jp.ac.it_college.std.s21015.pokemonquizapp

import android.content.res.AssetManager
import com.google.gson.Gson
import java.io.InputStreamReader
import kotlin.reflect.KClass

lateinit var pokedex: List<Dex>
lateinit var pokemon: List<PokemonData>

fun initJsonData(assets: AssetManager) {
    pokedex = parseJson(assets, "filtered_pokedex.json", FilteredPokedex::class).pokedex
    pokemon = parseJson(assets, "ordered_pokemon.json", Pokemon::class).pokemon
}

private fun <T : Any>parseJson(assets: AssetManager, file: String, c: KClass<T>): T {
    val inputStream = assets.open(file)
    val jsonReader = InputStreamReader(inputStream, "UTF-8").readText()
    inputStream.close()
    return Gson().fromJson(jsonReader, c.java)
}