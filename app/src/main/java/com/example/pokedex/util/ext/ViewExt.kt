package com.example.pokedex.util.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageFromUrl: String?) {
    if (imageFromUrl != null && imageFromUrl.isNotEmpty()) {
        GlideApp.with(view.context.applicationContext)
            .load(GlideUrlNoParams(imageFromUrl))
            .into(view)
    }
}