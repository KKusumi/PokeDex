package com.example.pokedex.util.ext

import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.airbnb.epoxy.EpoxyController
import com.example.pokedex.space
import com.example.pokedex.util.view.GlideApp
import com.example.pokedex.util.view.GlideUrlNoParams

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

@BindingAdapter("widthDp", "heightDp", requireAll = true)
fun FrameLayout.setLayoutParams(width: Int?, height: Int?) {
    width ?: return
    height ?: return
    layoutParams = FrameLayout.LayoutParams(width.toPx(), height.toPx())
}

fun EpoxyController.addSpace(width: Int, height: Int) {
    space {
        id("space")
        widthDp(width)
        heightDp(height)
    }
}