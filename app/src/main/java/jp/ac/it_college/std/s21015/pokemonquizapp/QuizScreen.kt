package jp.ac.it_college.std.s21015.pokemonquizapp

import android.annotation.SuppressLint
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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.picasso.Picasso
import jp.ac.it_college.std.s21015.pokemonquizapp.databinding.FragmentQuizScreenBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

class QuizScreen : Fragment() {
    private val baseUrl = "https://pokeapi.co/"
    private val iconUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/%s.png"

    private var _binding: FragmentQuizScreenBinding? = null
    private val binding get() = _binding!!
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
                    ?: throw IllegalStateException("????????????????????????????????????")
            } catch (e: Exception) {
                throw IllegalStateException("??????????????????????????????", e)
            }
        }
    }


    @SuppressLint("StringFormatMatches")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizScreenBinding.inflate(inflater, container, false)
        binding.pokemonIcon.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP)
        binding.pokemonIcon.bringToFront()

        showNextQuiz()

        return binding.root
    }

    private fun showNextQuiz() {

        binding.questionNum.text = getString(R.string.questionNum, quizCount)

        val id = pokemon[pokedex[args.generationNum].entries[Random().nextInt(args.pokemonNum)].pokemon_id-1].id
        val name = pokemon[pokedex[args.generationNum].entries[Random().nextInt(args.pokemonNum)].pokemon_id-1].name
        val name2 = pokemon[pokedex[args.generationNum].entries[Random().nextInt(args.pokemonNum)].pokemon_id-1].name
        val name3 = pokemon[pokedex[args.generationNum].entries[Random().nextInt(args.pokemonNum)].pokemon_id-1].name

        val quizData = mutableListOf(
            mutableListOf(id.toString(), pokemon[id-2].name, name, name2, name3)
        )

        val quiz = quizData[0]

        pokemonId(quiz[0])

        rightAnswer = quiz[1]

        quiz.removeAt(0)

        quiz.shuffle()

        binding.ansBtn.text = getString(R.string.answer, quiz[0])
        binding.ansBtn2.text = getString(R.string.answer, quiz[1])
        binding.ansBtn3.text = getString(R.string.answer, quiz[2])
        binding.ansBtn4.text = getString(R.string.answer, quiz[3])


        binding.ansBtn.setOnClickListener {
            checkAnswer(binding.ansBtn)
        }
        binding.ansBtn2.setOnClickListener {
            checkAnswer(binding.ansBtn2)
        }
        binding.ansBtn3.setOnClickListener {
            checkAnswer(binding.ansBtn3)
        }
        binding.ansBtn4.setOnClickListener {
            checkAnswer(binding.ansBtn4)
        }



    }


    private fun checkAnswer(button: Button) {
        val btnText = button.text.toString()

        val alertTitle: String
        if (btnText == rightAnswer) {
            alertTitle = "??????!"
            rightAnswerCount++
        } else {
            alertTitle = "?????????..."
        }

        AlertDialog.Builder(requireContext())
            .setTitle(alertTitle)
            .setMessage("?????? : $rightAnswer")
            .setPositiveButton("OK") { _, _ ->
                checkQuizCount()
            }
            .setCancelable(false)
            .show()
    }

    private fun checkQuizCount() {
        if (quizCount == QUIZ_COUNT) {
            view?.let {
                Navigation.findNavController(it).navigate(
                    QuizScreenDirections.actionQuizScreenToResultScreen().apply {
                        score = rightAnswerCount
                    }
                )
            }
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