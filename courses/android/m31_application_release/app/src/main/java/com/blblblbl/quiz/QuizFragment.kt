package com.blblblbl.quiz

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.blblblbl.quiz.databinding.FragmentQuizBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuizFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizFragment : Fragment() {
    lateinit var binding: FragmentQuizBinding
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
        binding = FragmentQuizBinding.inflate(layoutInflater)
        binding.bQiuzBack.setOnClickListener {
            findNavController().navigate(R.id.action_quizFragment_to_greetingFragment)

        }
        binding.bQiuzResults.setOnClickListener {
            val bundle = bundleOf(
                ANS1KEY to binding.q1.getAnswer(),
                ANS2KEY to binding.q2.getAnswer(),
                ANS3KEY to binding.q3.getAnswer())
            findNavController().navigate(R.id.action_quizFragment_to_resultsFragment,bundle)
        }
        val animation1 = AlphaAnimation(0.2f, 1.0f);
        animation1.setDuration(1000);
        animation1.setStartOffset(100);
        animation1.setFillAfter(true);
        binding.bQiuzBack.startAnimation(animation1);
        binding.bQiuzResults.startAnimation(animation1);
        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        const val ANS1KEY = "q1Ans"
        const val ANS2KEY = "q2Ans"
        const val ANS3KEY = "q3Ans"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QuizFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuizFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}