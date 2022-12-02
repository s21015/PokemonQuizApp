package jp.ac.it_college.std.s21015.pokemonquizapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import jp.ac.it_college.std.s21015.pokemonquizapp.databinding.FragmentGenerationScreenBinding
import java.security.SecureRandom
import java.util.*
import kotlin.random.Random.Default.nextInt

class GenerationScreen : Fragment() {
    private var _binding: FragmentGenerationScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGenerationScreenBinding.inflate(inflater, container, false)
        binding.zenkokuBtn.text = pokedex[0].name
        binding.kantoBtn.text = pokedex[1].name
        binding.zyoutoBtn.text = pokedex[2].name
        binding.houenBtn.text = pokedex[3].name
        binding.sinouBtn.text = pokedex[4].name
        binding.issyuBtn.text = pokedex[5].name
        binding.sentratuBtn.text = pokedex[6].name
        binding.kosutoBtn.text = pokedex[7].name
        binding.mauntenBtn.text = pokedex[8].name
        binding.aroraBtn.text = pokedex[9].name
        binding.gararuBtn.text = pokedex[10].name
        binding.yoroiBtn.text = pokedex[11].name
        binding.kanmuriBtn.text = pokedex[12].name
        binding.hisuiBtn.text = pokedex[13].name

        binding.zenkokuBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(
                GenerationScreenDirections.actionGenerationScreenToQuizScreen()
                    .apply {
                        generationNum = 0
                        pokemonNum = 897
                    }
            )
        }
        binding.kantoBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(
                GenerationScreenDirections.actionGenerationScreenToQuizScreen()
                    .apply {
                        generationNum = 1
                        pokemonNum = 150
                    }
            )
        }
        binding.zyoutoBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(
                GenerationScreenDirections.actionGenerationScreenToQuizScreen()
                    .apply {
                        generationNum = 2
                        pokemonNum = 250
                    }
            )
        }
        binding.houenBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(
                GenerationScreenDirections.actionGenerationScreenToQuizScreen()
                    .apply {
                        generationNum = 3
                        pokemonNum = 201
                    }
            )
        }
        binding.sinouBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(
                GenerationScreenDirections.actionGenerationScreenToQuizScreen()
                    .apply {
                        generationNum = 4
                        pokemonNum = 209
                    }
            )
        }
        binding.issyuBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(
                GenerationScreenDirections.actionGenerationScreenToQuizScreen()
                    .apply {
                        generationNum = 5
                        pokemonNum = 299
                    }
            )
        }
        binding.sentratuBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(
                GenerationScreenDirections.actionGenerationScreenToQuizScreen()
                    .apply {
                        generationNum = 6
                        pokemonNum = 149
                    }
            )
        }
        binding.kosutoBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(
                GenerationScreenDirections.actionGenerationScreenToQuizScreen()
                    .apply {
                        generationNum = 7
                        pokemonNum = 152
                    }
            )
        }
        binding.mauntenBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(
                GenerationScreenDirections.actionGenerationScreenToQuizScreen()
                    .apply {
                        generationNum = 8
                        pokemonNum = 150
                    }
            )
        }
        binding.aroraBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(
                GenerationScreenDirections.actionGenerationScreenToQuizScreen()
                    .apply {
                        generationNum = 9
                        pokemonNum = 402
                    }
            )
        }
        binding.gararuBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(
                GenerationScreenDirections.actionGenerationScreenToQuizScreen()
                    .apply {
                        generationNum = 10
                        pokemonNum = 399
                    }
            )
        }
        binding.yoroiBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(
                GenerationScreenDirections.actionGenerationScreenToQuizScreen()
                    .apply {
                        generationNum = 11
                        pokemonNum = 210
                    }
            )
        }
        binding.kanmuriBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(
                GenerationScreenDirections.actionGenerationScreenToQuizScreen()
                    .apply {
                        generationNum = 12
                        pokemonNum = 209
                    }
            )
        }
        binding.hisuiBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(
                GenerationScreenDirections.actionGenerationScreenToQuizScreen()
                    .apply {
                        generationNum = 13
                        pokemonNum = 241
                    }
            )
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}