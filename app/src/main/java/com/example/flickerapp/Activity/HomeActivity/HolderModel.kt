package com.example.flickerapp.Activity.HomeActivity

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flickerapp.R

class HolderModel(view: View):RecyclerView.ViewHolder(view) {
    var photoImageView :ImageView =view.findViewById(R.id.imageView)
    var photoTitle :TextView = view.findViewById(R.id.textView)
}