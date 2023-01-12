package com.blblblbl.myapplication.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.fragment.app.viewModels
import com.blblblbl.myapplication.R
import com.blblblbl.myapplication.viewModel.SubredditsFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubredditsFragment : Fragment() {
    private val viewModel:SubredditsFragmentViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                AuthScreen()
            }
        }
    }
    @Composable
    fun AuthScreen(){
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { viewModel.getSubreddits() }) {
                Text(text = "get popular")
            }
            Button(onClick = { viewModel.searchSubreddits() }) {
                Text(text = "search dog")
            }
            Button(onClick = { viewModel.meInfo() }) {
                Text(text = "me info")
            }
            Button(onClick = { viewModel.userInfo() }) {
                Text(text = "user info")
            }
        }
    }


}