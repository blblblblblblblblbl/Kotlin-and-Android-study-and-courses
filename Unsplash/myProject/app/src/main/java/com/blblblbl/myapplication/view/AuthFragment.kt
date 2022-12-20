package com.blblblbl.myapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.blblblbl.myapplication.databinding.FragmentAuthBinding
import com.blblblbl.myapplication.viewModel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFragment : Fragment() {
    private val viewModel:AuthViewModel by viewModels()
    lateinit var binding: FragmentAuthBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(layoutInflater)
        binding.blogin.setOnClickListener {
            viewModel.auth()
        }
        return binding.root
    }


}