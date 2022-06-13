package com.shivansh.officetask.adapters

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageViewLoadingAdapter {

    @BindingAdapter("image","empty")
    fun loadImage(view : ImageView, image : Bitmap?, empty : Drawable){
        val glide = Glide.with(view.context)
        if (image != null)
            glide.load(image).into(view)
        else glide.load(empty).into(view)
    }
}