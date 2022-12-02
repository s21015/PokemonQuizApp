package jp.ac.it_college.std.s21015.pokemonquizapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import jp.ac.it_college.std.s21015.pokemonquizapp.databinding.FragmentTitleScreenBinding

class TitleScreen : Fragment() {
    private var _binding: FragmentTitleScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTitleScreenBinding.inflate(inflater, container, false)

        binding.startButton.setOnClickListener {
            Navigation.findNavController(it).navigate(
                TitleScreenDirections.actionTitleScreenToGenerationScreen()
            )
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}