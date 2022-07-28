package com.lesson.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.lesson.fragment.databinding.Fragment1Binding


class BlankFragment : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    lateinit var binding: Fragment1Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=Fragment1Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.bSendToFrag2.setOnClickListener{
            dataModel.messageForFrag2.value="message to frag2 from frag1"
        }
        binding.bSendToAct.setOnClickListener{
            dataModel.messageForAct.value="message to act from frag1"
        }
        dataModel.messageForFrag1.observe(activity as LifecycleOwner,{
            binding.textViewMes.text = it
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = BlankFragment()
    }
}