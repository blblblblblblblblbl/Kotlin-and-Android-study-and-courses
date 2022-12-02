package com.blblblbl.tz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import com.blblblbl.tz.databinding.ActivityRegistrationBinding
import java.sql.Date

class RegistrationActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegistrationBinding
    private lateinit var viewModel: ViewModel
    lateinit var textWatcher: SimpleTextWatcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = (application as MyApplication).viewModel

        if(viewModel.checkSaved()) startActivity(Intent(this,MainActivity::class.java))

        binding.datePicker.setMaxDate(System.currentTimeMillis())
        binding.bRegister.isEnabled = false
        binding.bRegister.setOnClickListener { registrationOnCLick() }

        textWatcher = object : SimpleTextWatcher() {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.bRegister.isEnabled = !isDataEmpty()
            }
        }
        binding.textInputEditTextFirstName.addTextChangedListener(textWatcher)
        binding.textInputEditTextSecondName.addTextChangedListener(textWatcher)
        binding.textInputEditTextPassword.addTextChangedListener(textWatcher)
        binding.textInputEditTextConfirmPassword.addTextChangedListener(textWatcher)
    }

    fun isDataEmpty():Boolean{
        val data = Data()
        return ((data.firstName.isEmpty())|| (data.secondName.isEmpty())||
                (data.password.isEmpty())||
                (data.passwordConfirm.isEmpty()))
    }

    private fun registrationOnCLick(){
        val data = Data()
        if (isDataValid(data)) {
            registerUser(data)
            saveUser()
            startActivity(Intent(this,MainActivity::class.java))
        }
        else {
            Toast.makeText(this,getString(R.string.error_invalid_data),Toast.LENGTH_SHORT).show()
        }
        showErrorInvalidData(data)

    }

    private fun saveUser(){
        viewModel.saveUser()
    }

    private fun showErrorInvalidData(data:Data) {
        binding.apply {
            val a = data.firstName
            val b = !isFirstNameValid(data.firstName)
            textInputLayoutFirstName.error = getString(R.string.error_first_name_text)
            textInputLayoutFirstName.isErrorEnabled = !isFirstNameValid(data.firstName)

            textInputLayoutSecondName.error = getString(R.string.error_second_name_text)
            textInputLayoutSecondName.isErrorEnabled = !isSecondNameValid(data.secondName)

            textInputLayoutPassword.error = getString(R.string.error_password_text)
            textInputLayoutPassword.isErrorEnabled = !isPasswordValid(data.password,data.passwordConfirm)

            textInputLayoutConfirmPassword.error = getString(R.string.error_password_text)
            textInputLayoutConfirmPassword.isErrorEnabled = textInputLayoutPassword.isErrorEnabled
        }
    }

    private fun isDataValid(data:Data):Boolean {
        data.apply {
            return (isFirstNameValid(firstName) && isSecondNameValid(secondName) && isDateValid(date) && isPasswordValid(password,passwordConfirm))
        }
    }
    private fun isFirstNameValid(firstName:String):Boolean{
        return ((firstName.length >=2) && (firstName.length <=20))
    }
    private fun isSecondNameValid(secondName:String):Boolean{
        return ((secondName.length >=2) && (secondName.length <=20))
    }
    private fun isDateValid(date: java.util.Date):Boolean{
        return true
    }
    private fun isPasswordValid(password:String, passwordConfirm:String):Boolean{
        return ((password==passwordConfirm) && (password.length>5))
    }

    private fun registerUser(data:Data){
        data.apply {
            viewModel.registerUser(firstName,secondName,date,password)
        }
    }

    inner class Data{
        var  firstName = binding.textInputEditTextFirstName.text.toString()
        var  secondName = binding.textInputEditTextSecondName.text.toString()
        var  datePicker = binding.datePicker
        var  date = Date(datePicker.year,datePicker.month,datePicker.dayOfMonth)
        var  password = binding.textInputEditTextPassword.text.toString()
        var  passwordConfirm = binding.textInputEditTextConfirmPassword.text.toString()
        fun updateData(){
            firstName = binding.textInputEditTextFirstName.text.toString()
            secondName= binding.textInputEditTextSecondName.text.toString()
            datePicker = binding.datePicker
            date = Date(datePicker.year,datePicker.month,datePicker.dayOfMonth)
            password = binding.textInputEditTextPassword.text.toString()
            passwordConfirm = binding.textInputEditTextConfirmPassword.text.toString()
        }
    }
}