package com.example.flickerapp.Activity.HomeActivity

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flickerapp.Activity.FullScreenActivity.FullScreenActivity
import com.example.flickerapp.Models.Photo
import com.example.flickerapp.R
import com.example.flickerapp.utils.Constants
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.photo_item.view.*
import java.lang.Exception


class PhotoAdapter(private val photosList: ArrayList<Photo>):RecyclerView.Adapter<PhotoAdapter.PhotoHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        //Creating PhotoHolder with photo_item.layout which is created by hand
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.photo_item,parent,false)
        return PhotoHolder(inflatedView)

    }

    override fun getItemCount()= photosList.size

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {//Bind each photo to holder
        val currentPhoto = photosList[position]
        holder.photoBind(currentPhoto)
    }


    class PhotoHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var photo: Photo? = null
        private var imgUrl : String? = null

        init {
            v.setOnClickListener(this)//"this" view has a click listener so each photo has
        }

        fun photoBind(photo: Photo){
            this.photo = photo
            //Get thumbnail photo overhere
            this.imgUrl = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_t.png"
            Picasso.get()
                .load(imgUrl)
                .into(view.image_view_rcy,object :Callback{ //Callback for photos progress bar
                    override fun onSuccess() {
                        view.progressBarForEachPhoto.visibility = View.GONE
                    }
                    override fun onError(e: Exception?) {
                        TODO("Not yet implemented")
                    }
                })
        }

        override fun onClick(v: View) {//Implementing intent logic and start activity
            val context = v.context
            val photoIntent = Intent(context,FullScreenActivity::class.java)
            photoIntent.putExtra(Constants.GET_INTENT_PHOTO,photo)
            context.startActivity(photoIntent)
        }

    }
}

//
