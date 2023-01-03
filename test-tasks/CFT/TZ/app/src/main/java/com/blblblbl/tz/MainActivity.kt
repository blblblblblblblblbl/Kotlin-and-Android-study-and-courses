package com.blblblbl.tz

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.blblblbl.tz.databinding.ActivityMainBinding
import com.blblblbl.tz.databinding.ActivityRegistrationBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = (application as MyApplication).viewModel

        binding.buttonMainScreenHello.setOnClickListener { buttonHelloOnClick() }
    }

    fun buttonHelloOnClick(){
        val dialog = Dialog(this)
        //dialog.setTitle("hello name")
        dialog.setContentView(R.layout.dialog)
        dialog.findViewById<TextView>(R.id.textViewDialog).text = "hello ${viewModel.getUser().firstName}"
        dialog.show()
    }
}