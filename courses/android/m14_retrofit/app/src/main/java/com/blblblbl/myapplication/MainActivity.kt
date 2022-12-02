package com.blblblbl.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.blblblbl.myapplication.data_classes.RandomUserJson2KtKotlin
import com.blblblbl.myapplication.data_classes.Results
import com.blblblbl.myapplication.databinding.ActivityMainBinding
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        runBlocking{
            val user = RetrofitServices.searchRandomUserApi.getRandomUser()
            userToScreen(user.results.first())
        }
        setContentView(binding.root)
        Log.d("myLog","sdfg")

        binding.bReload.setOnClickListener {
            lifecycleScope.launch {
                val user = RetrofitServices.searchRandomUserApi.getRandomUser()
                userToScreen(user.results.first())
            }
        }
    }
    fun userToScreen(user: Results){
        binding.apply {
            tvgender.text = "gender: ${user.gender}"
            tvname.text = "name: ${user.name?.title} ${user.name?.first} ${user.name?.last}"
            tvlocation.text = "location: ${user.location?.city} ${user.location?.country}"
            tvemail.text = "email: ${user.email}"
            tvusername.text = "username: ${user.login?.username}"
            tvpassword.text = "password: ${user.login?.password}"
            tvphone.text = "phone: ${user.phone}"
            tvcell.text = "cell: ${user.cell}"
            Glide.with(applicationContext).load(user.picture?.large).into(binding.ivAvatar)
        }
    }
}