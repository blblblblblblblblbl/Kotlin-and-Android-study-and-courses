package com.blblblbl.myapplication

import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.blblblbl.myapplication.data.data_classes.PhotoDto
import com.blblblbl.myapplication.data.data_classes.Photos
import com.blblblbl.myapplication.databinding.RecyclerViewItemBinding
import com.blblblbl.myapplication.entity.Photo
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideContext
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class CustomAdapter(
    private val photos: Photos,
    private val onClick:(PhotoDto)->Unit
):Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //val binding = RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context))
        //так было в видео на skillboxe, но тогда matchparent по ширине у элемента recyclerview не будет работать
        //потому что надо бередать parenta как ниже
        val binding = RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = photos.photoDtos[position]
        holder.binding.apply {
            tvCamera.text = item.camera?.name ?: "none"
            tvDate.text = item.earthDate ?: "none"
            tvRover.text = item.rover?.name ?: "none"
            tvSol.text = item.sol.toString()
            var requestOptions = RequestOptions()

            val src = item.imgSrc.toString().replace("http","https").replace("jpl.","")
            //очень странная штука ссылку нужно изменить чтобы она работала, об этом лучше бы заранее в задании говорить
            //на нахождение ошибки много времени уходит, потому что сложно понять что не так
            //если в браузере все работает
            //пример
            //нужно
            //https://mars.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/fcam/FLB_486265257EDR_F0481570FHAZ00323M_.JPG
            //было
            //http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/fcam/FLB_486265257EDR_F0481570FHAZ00323M_.JPG
            Glide.with(ivPhoto.context)
                .load(src)
                //.fitCenter()
                .placeholder(R.drawable.photo)
                .into(ivPhoto)
            holder.binding.root.setOnClickListener{
                item?.let { onClick(item) }
            }

        }
    }

    override fun getItemCount(): Int = photos.photoDtos.size
}