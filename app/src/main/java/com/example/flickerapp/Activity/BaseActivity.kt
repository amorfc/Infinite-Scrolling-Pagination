package com.example.flickerapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.flickerapp.Models.ApiRes
import com.example.flickerapp.Models.Photo
import com.example.flickerapp.Models.Photos
import com.example.flickerapp.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseActivity : AppCompatActivity() {
    lateinit var photos : ArrayList<Photo>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
}

