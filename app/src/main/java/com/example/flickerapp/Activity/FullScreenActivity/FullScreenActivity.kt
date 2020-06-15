package com.example.flickerapp.Activity.FullScreenActivity


import android.os.Bundle
import android.telecom.Call
import android.view.View
import androidx.core.net.toUri
import com.example.flickerapp.Activity.BaseActivity
import com.example.flickerapp.Models.Photo
import com.example.flickerapp.R
import com.example.flickerapp.utils.Constants
import com.github.chrisbanes.photoview.PhotoView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.full_screen_photo.*
import java.lang.Exception


class FullScreenActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.full_screen_photo)
        val photo : Photo = intent.getSerializableExtra(Constants.GET_INTENT_PHOTO) as Photo
        Picasso
            .get()
            .load("https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_b.png")
            .into(photoView,object : Callback{
                override fun onSuccess() {
                    fullPhotoProgressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    TODO("Not yet implemented")
                }
            })

    }
}
/*
*/