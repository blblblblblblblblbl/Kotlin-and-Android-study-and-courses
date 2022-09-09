package com.blblblbl.inputlesson

import android.hardware.input.InputManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.blblblbl.inputlesson.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private companion object{
        const val SCREEN_STATE = "screen_State"
        const val INITIAL = 0
        const val PROGRESS = 1
        const val SUCCESS = 2
        const val FAILED = 3
    }

    private var state = INITIAL
    lateinit var binding: ActivityMainBinding
    lateinit var textWatcher: SimpleTextWatcher
    fun AppCompatActivity.hideKeyboard(view: View){
        val imm = this.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken,0)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val textInputLayout =binding.textInputLayout
        val textInputEditText = binding.textInputEditText
        val checkBox = binding.checkbox
        val spannableString = getString(R.string.agreement)
        binding.bLogin.isEnabled = false
        checkBox.text = spannableString
        checkBox.setOnCheckedChangeListener{
                _,isChecked-> binding.bLogin.isEnabled = isChecked
        }
        textWatcher = object : SimpleTextWatcher() {
            override fun afterTextChanged(str: Editable?) {
                val input = str.toString()
                val valid = android.util.Patterns.EMAIL_ADDRESS.matcher(str.toString()).matches()
                if (valid){
                    binding.bLogin.isEnabled = true
                    textInputLayout.isErrorEnabled = false
                }
                /*val input = str.toString()
                val valid = android.util.Patterns.EMAIL_ADDRESS.matcher(str.toString()).matches()
                if (input.endsWith("@g")){
                    //binding.textInputEditText.setText("${input}mail.com")
                    binding.textInputEditText.setTextCorrectly("${input}mail.com")
                    return
                }
                textInputLayout.isErrorEnabled = !valid
                if (!valid) textInputLayout.error = getString(R.string.invalid_email_address)
                else{
                    Toast.makeText(this@MainActivity,getString(R.string.valid_email_address),Toast.LENGTH_LONG).show()
                }*/
            }
            fun TextInputEditText.setTextCorrectly(text:CharSequence){
                setText(text)
                setSelection(text.length)
            }
            //fun TextInputEditText.listenChanges{textInputLayout.isErrorEnabled = false}
        }

        binding.bLogin.setOnClickListener{loginOnCLickListener(it)}
        textInputEditText.addTextChangedListener(textWatcher)
        setContentView(binding.root)
        savedInstanceState?.let {
            state = it.getInt(SCREEN_STATE)
            Log.d("MyLog",state.toString())
        }
        if (state== FAILED) showDialog(binding.linearLayout)
    }

    fun loginOnCLickListener(it:View){
        val textInputEditText = binding.textInputEditText
        val textInputLayout = binding.textInputLayout
        val valid = android.util.Patterns.EMAIL_ADDRESS.matcher(textInputEditText.text).matches()
        if (valid) {
            Snackbar.make(binding.bLogin,"go to post login", Snackbar.LENGTH_SHORT).show()
            hideKeyboard(textInputEditText)
            binding.linearLayout.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            state = PROGRESS
            Handler(Looper.myLooper()!!).postDelayed({
                state = FAILED
                binding.linearLayout.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
                showDialog(binding.linearLayout)
                /* val builder = AlertDialog.Builder(this)
                 builder.setTitle(R.string.attention)
                     .setPositiveButton(R.string.close){
                         dialog, id->dialog.cancel()
                     }
                 builder.create()
                 builder.show()*/
            },3000)
            //textInputLayout.isErrorEnabled = false

        }
        else{
            textInputLayout.error = getString(R.string.invalid_email_address)
            binding.bLogin.isEnabled = false
        }
    }
    private fun showDialog(viewGroup: ViewGroup){
        val dialog = BottomSheetDialog(this@MainActivity)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog,viewGroup,false)
        dialog.run {
            setCancelable(false)
            view.findViewById<View>(R.id.bDialogClose).setOnClickListener{
                state = INITIAL
                dialog.dismiss()
            }
            setContentView(view)
            show()
        }
    }
    override fun onResume() {
        super.onResume()
        binding.textInputEditText.addTextChangedListener(textWatcher)
    }

    override fun onPause() {
        super.onPause()
        binding.textInputEditText.removeTextChangedListener(textWatcher)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("MyLog","saved state ${state.toString()}")
        outState.putInt(SCREEN_STATE,state)
    }
}