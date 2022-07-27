package com.recycleviewer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.recycleviewer.databinding.ActivityEditBinding
import com.recycleviewer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var launcher:ActivityResultLauncher<Intent>?=null
    private var launcherView:ActivityResultLauncher<Intent>?=null
    private val plantAdapter = PlantAdapter()
    private val imageIdList = listOf(
        R.drawable.plant_img1,
        R.drawable.plant_img2,
        R.drawable.plant_img3,
        R.drawable.plant_img4)
    private  var index = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    private fun init()
    {
        binding.apply {
            recView.layoutManager = GridLayoutManager(this@MainActivity,3)
            recView.adapter = plantAdapter
            /*button.setOnClickListener {
                if (index == 4) { index = 0}
                val plant = Plant(imageIdList[index],"plant${index}")
                plantAdapter.addPlant(plant)
                index++
            }*/
        }
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        {
                result:ActivityResult->
            if (result.resultCode== RESULT_OK)
            {
                /*var imgId = result.data?.getIntExtra("img",0)
                Log.d("MyLog",imgId.toString())
                var titleRes = result.data?.getStringExtra("title")
                Log.d("MyLog",titleRes.toString())
                var descRes = result.data?.getStringExtra("desc")
                Log.d("MyLog",descRes.toString())
                var plant = Plant(imgId!!,titleRes!!,descRes!!)
                plantAdapter.addPlant(plant)*/
                plantAdapter.addPlant(result.data?.getSerializableExtra("plant") as Plant)
            }
        }
        launcherView = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        {
        }
    }
    fun addOnclick(view: View)
    {
        var intent:Intent = Intent(this, EditActivity::class.java)
        //startActivity(intent)
        launcher?.launch(intent)
    }
    fun plantOnClick(view: View)
    {
        Log.d("MyLog",view.tag.toString())
        var intent = Intent(this,ViewActivity::class.java)
        var plantIndex = view.tag.toString().replace("plant","").toInt()
        var plant = plantAdapter.plantList[plantIndex]
        intent.putExtra("plant",Plant(plant.imageId,plant.title,plant.desc))
        launcherView?.launch(intent)
    }
}