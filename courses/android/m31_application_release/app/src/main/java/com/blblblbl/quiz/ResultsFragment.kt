package com.blblblbl.quiz

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.navigation.fragment.findNavController
import com.blblblbl.quiz.databinding.FragmentResultsBinding
import kotlinx.coroutines.NonCancellable.start

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ResultsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultsFragment : Fragment() {
    lateinit var binding: FragmentResultsBinding
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultsBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        binding.tvResults.text = checkAnswers()
        binding.bRestart.setOnClickListener {
            findNavController().navigate(R.id.action_resultsFragment_to_quizFragment)
        }
        val animation1 = AlphaAnimation(0.2f, 1.0f);
        animation1.setDuration(1000);
        animation1.setStartOffset(100);
        animation1.setFillAfter(true);
        binding.tvResults.startAnimation(animation1)
        binding.bRestart.startAnimation(animation1)
        /*ObjectAnimator.ofFloat(binding.tvResults, "translationX", 100f).apply {
            duration = 500
            start()
        }
        ObjectAnimator.ofFloat(binding.bRestart, "translationX", 100f).apply {
            duration = 500
            start()
        }*/
        return binding.root
    }

    private fun checkAnswers(): CharSequence? {
        var answers = mutableListOf<Boolean>()
        answers.add(arguments?.getInt(QuizFragment.ANS1KEY,0) == 1)
        answers.add(arguments?.getInt(QuizFragment.ANS2KEY,0) == 2)
        answers.add(arguments?.getInt(QuizFragment.ANS3KEY,0) == 1)
        val rightAnswers = answers.filter { it }

        if (rightAnswers.size == answers.size) binding.animviewResults.setAnimation(R.raw.excellent_animation)
        else binding.animviewResults.setAnimation(R.raw.cat_animation)

        return "you have ${rightAnswers.size} right of ${answers.size}"
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ResultsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ResultsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}