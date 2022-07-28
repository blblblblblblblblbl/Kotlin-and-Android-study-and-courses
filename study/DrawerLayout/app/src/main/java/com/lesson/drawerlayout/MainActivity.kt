package com.lesson.drawerlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.lesson.drawerlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    fun openOnClick(view: View)
    {
        binding.apply {
            drawer.openDrawer(GravityCompat.START)
            navigationView.setNavigationItemSelectedListener {
                when(it.itemId)
                {
                    R.id.item1->{ Toast.makeText(this@MainActivity,"item1",Toast.LENGTH_SHORT).show()}
                    R.id.item11->Toast.makeText(this@MainActivity,"item11",Toast.LENGTH_SHORT).show()
                }
                //drawer.close()
                true
            }
        }
    }
}