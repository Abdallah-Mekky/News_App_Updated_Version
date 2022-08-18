package com.task1.news

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImageFromURL")
fun loadImage(imageView: ImageView, url: String) {

    Glide.with(imageView)
        .load(url)
        .into(imageView)
}