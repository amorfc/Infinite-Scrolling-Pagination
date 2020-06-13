package com.example.flickerapp.Activity.HomeActivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flickerapp.Models.Photo
import com.example.flickerapp.R
import com.squareup.picasso.Picasso

class Adapter(private val arrayList: ArrayList<Photo>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private val VIEW_TYPE_ITEM = 0 //Normal item
    private val VIEW_TYPE_LOADING = 1 //Yükleniyor

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var layoutInflater:LayoutInflater = LayoutInflater.from(parent.context)
        return HolderModel(layoutInflater.inflate(R.layout.photo_item,parent,false))
    }


    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var photo : Photo = arrayList[position]
        var photoHolder :HolderModel = holder as HolderModel
        photoHolder.photoTitle.text = photo.title
        Picasso.get().load("https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.png").into(photoHolder.photoImageView);
    }

}