package com.example.flickerapp.Activity.FullScreenActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import com.example.flickerapp.Activity.BaseActivity
import com.example.flickerapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.full_screen_photo.view.*

const val PHOTO_IMAGE_URL= "PHOTO_IMAGE_URL"

class FullScreenActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.full_screen_photo)
        val imgUrl = intent.getStringExtra("PHOTO")
        val imgview :ImageView= findViewById(R.id.full_screen_image_view)
        Picasso.get().load(imgUrl).into(imgview)
    }
}
/*
*/