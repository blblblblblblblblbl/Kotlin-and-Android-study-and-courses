package com.market.catfacts.view.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.market.catfacts.databinding.ActivityCatFactBinding
import com.market.catfacts.model.CatFact
import com.market.catfacts.view.viewmodel.CatFactViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class CatFactActivity:AppCompatActivity() {
    private val catFactViewModel:CatFactViewModel by viewModels()
    private lateinit var binding: ActivityCatFactBinding
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityCatFactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCatFactLoadNew.setOnClickListener{
            catFactViewModel.loadCatFact()
        }
        catFactViewModel.catFact.observe(this,{newCatFact->
            binding.txtViewCatFact.text = newCatFact.fact})
        /*val catFactObserver = Observer<CatFact>{ newCatFact->
                binding.txtViewCatFact.text = newCatFact.fact
        }*/
        //catFactViewModel.catFact.observe(this,catFactObserver)
        catFactViewModel.loadCatFact()
    }
}