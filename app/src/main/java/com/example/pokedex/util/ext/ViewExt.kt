package com.example.pokedex.util.ext

import android.view.View
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

@BindingAdapter(value = ["visibleGone"])
fun showHide(view: View, show: Boolean?) {
    view.visibility = if (show == true) View.VISIBLE else View.GONE
}