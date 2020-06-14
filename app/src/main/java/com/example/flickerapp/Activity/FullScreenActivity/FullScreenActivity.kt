package com.example.flickerapp.Activity.FullScreenActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import com.example.flickerapp.Activity.BaseActivity
import com.example.flickerapp.Activity.HomeActivity.HomeActivity
import com.example.flickerapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.full_screen_photo.*
import kotlinx.android.synthetic.main.full_screen_photo.view.*
import kotlinx.android.synthetic.main.full_screen_photo.view.full_screen_image_view

const val PHOTO= "PHOTO"

class FullScreenActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.full_screen_photo)
        val imgUrl = intent.getStringExtra(HomeActivity.INTENT_PHOTO_KEY)
        Picasso.get().load(imgUrl).into(full_screen_image_view)
    }
}
/*
*/