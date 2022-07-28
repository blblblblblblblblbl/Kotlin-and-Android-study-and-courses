package com.lesson.toolbarlesson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "blblbl"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId== android.R.id.home) finish()
        when(item.itemId)
        {
            android.R.id.home->finish()
            R.id.save->{
                Toast.makeText(this,"save",Toast.LENGTH_SHORT).show()
            }
            R.id.bug->{
                Toast.makeText(this,"bug",Toast.LENGTH_SHORT).show()
            }
            R.id.search->{
                Toast.makeText(this,"search",Toast.LENGTH_SHORT).show()
            }
            R.id.sync->{
                Toast.makeText(this,"sync",Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }
}