package com.lesson.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.lesson.fragment.databinding.Fragment2Binding

class BlankFragment2 : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    lateinit var binding: Fragment2Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Fragment2Binding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.bSendToFrag1.setOnClickListener{
            dataModel.messageForFrag1.value="message to frag1 from frag2"
        }
        binding.bSendToAct.setOnClickListener{
            dataModel.messageForAct.value="message to act from frag2"
        }
        dataModel.messageForFrag2.observe(activity as LifecycleOwner,{
            binding.textViewMes.text = it
        })
    }
    companion object {
        fun newInstance() = BlankFragment2()
    }
}