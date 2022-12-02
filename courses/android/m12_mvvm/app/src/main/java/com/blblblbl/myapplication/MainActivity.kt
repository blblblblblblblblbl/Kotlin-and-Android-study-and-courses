package com.blblblbl.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope

import com.blblblbl.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: MyViewModel by viewModels()
    lateinit var textWatcher: SimpleTextWatcher
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            bSearch.setOnClickListener {
                val text = textEditMain.text.toString()
                viewModel.search(text)
                lifecycleScope.launchWhenStarted {
                    viewModel.state
                        .collect{ state->
                            when(state){
                                State.Fail -> {
                                    bSearch.isEnabled=true
                                    progressCircular.visibility = View.INVISIBLE
                                    tvMain.text = "${getString(R.string.fail_str)} ${viewModel.searchStr} "
                                }
                                State.Loading -> {
                                    bSearch.isEnabled=false
                                    progressCircular.visibility = View.VISIBLE
                                    tvMain.text = "${getString(R.string.loading_str)} ${viewModel.searchStr} "
                                }
                                State.Success -> {
                                    bSearch.isEnabled=true
                                    progressCircular.visibility = View.INVISIBLE
                                    tvMain.text = "${getString(R.string.success_str)} ${viewModel.searchStr} "
                                }
                            }
                        }
                }
            }
            textWatcher = object : SimpleTextWatcher() {
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    bSearch.isEnabled = p0.toString().length >= 3
                }
            }
            textEditMain.addTextChangedListener(textWatcher)
        }
    }
}
