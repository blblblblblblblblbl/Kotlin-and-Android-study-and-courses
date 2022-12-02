package com.blblblbl.myapplication.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.DifferCallback
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.blblblbl.myapplication.R
import com.blblblbl.myapplication.databinding.FragmentMainBinding
import com.blblblbl.myapplication.presentation.MainViewModel
import com.example.example.Results
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel:MainViewModel by viewModels()
    private val pagedAdapter = CustomAdapter(){onItemClick(it)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        pagedAdapter.addLoadStateListener { listener(it) }
        binding.rvMain.adapter = pagedAdapter.withLoadStateFooter(MyLoadStateAdapter())
        viewModel.pagedCharacters.onEach {
            pagedAdapter.submitData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
        return binding.root
    }
    private fun listener(loadState: CombinedLoadStates){
        val errorState = when {
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            loadState.prepend is LoadState.Error ->  loadState.prepend as LoadState.Error
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            else -> null
        }
        errorState?.let {
            AlertDialog.Builder(requireContext())
                .setMessage("Internet connection error")
                .setPositiveButton("retry") { _, _ ->
                    pagedAdapter.retry()
                }
                .setCancelable(false)
                .create()
                .show()
        }
    }
    private fun onItemClick(item: Results){
        val bundle =  bundleOf()
        bundle.putParcelable(RESULTS_KEY,item)
        findNavController().navigate(R.id.action_mainFragment_to_characterDetailsFragment,bundle)
    }
    companion object{
        const val RESULTS_KEY = "results_key"
    }
}