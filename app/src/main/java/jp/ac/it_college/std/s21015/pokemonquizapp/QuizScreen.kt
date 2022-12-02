package jp.ac.it_college.std.s21015.pokemonquizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.UiThread
import androidx.annotation.WorkerThread
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewbinding.ViewBinding
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.picasso.Picasso
import jp.ac.it_college.std.s21015.pokemonquizapp.databinding.FragmentQuizScreenBinding
import jp.ac.it_college.std.s21015.pokemonquizapp.databinding.FragmentTitleScreenBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

class QuizScreen : Fragment() {
    private val baseUrl = "https://pokeapi.co/"
    private val iconUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/%s.png"

    private lateinit var binding: FragmentQuizScreenBinding

    private var _binding: FragmentQuizScreenBinding? = null
//    private val binding get() = _binding!!
    private val args: QuizScreenArgs by navArgs()
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private var rightAnswer: String? = null
    private var rightAnswerCount = 0
    private var quizCount = 1
    private val QUIZ_COUNT = 10

    @UiThread
    private fun pokemonId(pokemon: String) {
        lifecycleScope.launch {
            val info = getPokemonId(pokemon)
            Picasso.get().load(iconUrl.format(info.id))
                .into(binding.pokemonIcon)
        }
    }

    @WorkerThread
    @Suppress("BlockingMethodInNonBlockingContext")
    private suspend fun getPokemonId(pokemon: String): PokeApi {
        return withContext(Dispatchers.IO) {
            val retrofit = Retrofit.Builder().apply {
                baseUrl(baseUrl)
                addConverterFactory(MoshiConverterFactory.create(moshi))
            }.build()
            val service: PokemonService = retrofit.create(PokemonService::class.java)
            try {
                service.fetchPokemon(pokemonNum = pokemon).execute().body()
                    ?: throw IllegalStateException("ポケモン情報が取れません")
            } catch (e: Exception) {
                throw IllegalStateException("例外が発生しました。", e)
            }
        }
    }


    @SuppressLint("StringFormatMatches")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizScreenBinding.inflate(inflater, container, false)

//        binding.pokemonIcon.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP)

        showNextQuiz()

        return binding.root
    }

    private fun showNextQuiz() {

//        val id = pokemon[pokedex[args.generationNum].entries[Random().nextInt(args.pokemonNum)].pokemon_id-1].id
        val id = pokemon[10].id
        val name = pokemon[pokedex[args.generationNum].entries[Random().nextInt(args.pokemonNum)].pokemon_id-1].name
        val name2 = pokemon[pokedex[args.generationNum].entries[Random().nextInt(args.pokemonNum)].pokemon_id-1].name
        val name3 = pokemon[pokedex[args.generationNum].entries[Random().nextInt(args.pokemonNum)].pokemon_id-1].name
//        val id = pokemon[pokedex[0].entries[Random().nextInt(args.pokemonNum)].pokemon_id-1].id
//        val name = pokemon[pokedex[args.generationNum].entries[Random().nextInt(args.pokemonNum)].pokemon_id].name
//        val name2 = pokemon[pokedex[args.generationNum].entries[Random().nextInt(args.pokemonNum)].pokemon_id].name
//        val name3 = pokemon[pokedex[args.generationNum].entries[Random().nextInt(args.pokemonNum)].pokemon_id].name

        val quizData = mutableListOf(
            mutableListOf(id.toString(), pokemon[id-1].name, name, name2, name3)
        )

        val quiz = quizData[0]

        pokemonId(quiz[0])

        rightAnswer = quiz[1]

        quiz.removeAt(0)

        quiz.shuffle()

        binding.ansBtn.text = quiz[0]
        binding.ansBtn2.text = quiz[1]
        binding.ansBtn3.text = quiz[2]
        binding.ansBtn4.text = quiz[3]


        binding.ansBtn.setOnClickListener {
            checkAnswer()
        }
        binding.ansBtn2.setOnClickListener {
            checkAnswer()
        }
        binding.ansBtn3.setOnClickListener {
            checkAnswer()
        }
        binding.ansBtn4.setOnClickListener {
            checkAnswer()
        }

    }


    private fun checkAnswer() {
         val answerBtn = binding.ansBtn
         val btnText = answerBtn.text.toString()

        val alertTitle: String
        if (btnText == rightAnswer) {
            alertTitle = "正解!"
            rightAnswerCount++
        } else {
            alertTitle = "不正解..."
        }

        AlertDialog.Builder(requireContext())
            .setTitle(alertTitle)
            .setMessage("答え : $rightAnswer")
            .setPositiveButton("OK") { _, _ ->
                checkQuizCount()
            }
            .setCancelable(false)
            .show()
    }

    private fun checkQuizCount() {
        if (quizCount == QUIZ_COUNT) {
//            val intent = Intent(this@QuizScreen, ResultScreen::class.java)
//            intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount)
//            startActivity(intent)
        } else {
            quizCount++
            showNextQuiz()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
