package com.example.flickerapp.Activity.HomeActivity

import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.flickerapp.R

class HolderModel(view: View):RecyclerView.ViewHolder(view) {
    var photoImageView :ImageView =view.findViewById(R.id.image_view_rcy)
    var photoLayout : ConstraintLayout = view.findViewById(R.id.photos_Constraint_Layout)
}