package jp.ac.it_college.std.s21015.pokemonquizapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import jp.ac.it_college.std.s21015.pokemonquizapp.databinding.FragmentResultScreenBinding

class ResultScreen : Fragment() {
    private var _binding: FragmentResultScreenBinding? = null
    private val binding get() = _binding!!
    private val args: ResultScreenArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultScreenBinding.inflate(inflater, container,false)
        binding.scoreText.text = getString(R.string.score, args.score)
        binding.returnBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(
                ResultScreenDirections.actionResultScreenToGenerationScreen()
            )
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}