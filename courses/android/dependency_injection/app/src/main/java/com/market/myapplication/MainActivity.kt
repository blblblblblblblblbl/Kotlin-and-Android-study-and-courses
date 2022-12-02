package com.market.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.market.myapplication.dagger_bicycle.BicycleFactory
import com.market.myapplication.dagger_bicycle.BicycleModule
import com.market.myapplication.dagger_bicycle.DaggerBicycleComponent
import com.market.myapplication.databinding.ActivityMainBinding
import com.market.myapplication.draft.DaggerDaggerInfoComponent
import com.market.myapplication.draft.DaggerInfoComponent
import com.market.myapplication.draft.Info
import com.market.myapplication.koin_bicycle.bicycleModule
import dagger.Module
import org.koin.android.ext.android.inject
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var dagger: DaggerBicycleComponent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.bDagger.setOnClickListener { bDaggerOnClick() }
        binding.bKoin.setOnClickListener { bKoinOnClick() }
        dagger = (application as MyApp).dagger
        setContentView(binding.root)
    }
    private fun bDaggerOnClick(){
        val bicycleFactory1 =dagger.getBicycleFactory()
        val bicycle = bicycleFactory1.build("AAA","Black")
        Toast.makeText(this,bicycle.toString(),Toast.LENGTH_SHORT).show()
        Log.d("MyLog",bicycle.toString())
    }
    private fun bKoinOnClick(){
        val bicycleFactory1 : BicycleFactory by inject()
        val bicycle = bicycleFactory1.build("AAA","Black")
        Toast.makeText(this,bicycle.toString(),Toast.LENGTH_SHORT).show()
        Log.d("MyLog",bicycle.toString())
    }
}