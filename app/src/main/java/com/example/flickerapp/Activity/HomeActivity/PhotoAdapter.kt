package com.example.flickerapp.Activity.HomeActivity

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flickerapp.Activity.FullScreenActivity.FullScreenActivity
import com.example.flickerapp.Models.Photo
import com.example.flickerapp.R
import com.example.flickerapp.inflate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.photo_item.view.*

class PhotoAdapter(private val photosList: ArrayList<Photo>):RecyclerView.Adapter<PhotoAdapter.PhotoHolder>(){
    private val VIEW_TYPE_ITEM = 0 //Normal item
    private val VIEW_TYPE_LOADING = 1 //Yükleniyor


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        val inflatedView = parent.inflate(R.layout.photo_item,false)
        return PhotoHolder(inflatedView)
    }

    override fun getItemCount()= photosList.size

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        val currentPhoto = photosList[position]
        holder.photoBind(currentPhoto)
    }

    class PhotoHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var photo: Photo? = null
        private var imgUrl : String? = null
        init {
            v.setOnClickListener(this)
        }

        fun photoBind(photo: Photo){
            this.photo = photo
            this.imgUrl = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_b.png"
            Picasso.get().load(imgUrl).into(view.image_view_rcy)
        }

        override fun onClick(v: View) {
            val context = v.context
            val photoIntent = Intent(context,FullScreenActivity::class.java)
            photoIntent.putExtra(PHOTO_KEY,imgUrl)
            context.startActivity(photoIntent)
            Log.d("RecyclerView", "CLICK!")
        }

        companion object {
            private val PHOTO_KEY = "PHOTO"
        }
    }
}

//
