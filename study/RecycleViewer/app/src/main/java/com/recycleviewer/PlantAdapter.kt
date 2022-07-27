package com.recycleviewer

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.recycleviewer.PlantAdapter.*
import com.recycleviewer.databinding.PlantItemBinding

class PlantAdapter:RecyclerView.Adapter<PlantHolder>() {
    val plantList = ArrayList<Plant>()
    class PlantHolder(item: View):RecyclerView.ViewHolder(item) {
        val binding = PlantItemBinding.bind(item)
        fun bind(plant: Plant) = with(binding)
        {
            imageView.setImageResource(plant.imageId)
            textView.text = plant.title
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.plant_item,parent,false)
        if (plantList.size==0) view.tag = "plant${plantList.size}"
        else view.tag = "plant${plantList.size-1}"
        Log.d("MyLog",view.tag.toString())
        return PlantHolder(view)
    }

    override fun onBindViewHolder(holder: PlantHolder, position: Int) {
        holder.bind(plantList[position])
    }

    override fun getItemCount(): Int {
        return plantList.size
    }
    fun addPlant(plant: Plant)
    {
        plantList.add(plant)
        notifyDataSetChanged()
    }
}