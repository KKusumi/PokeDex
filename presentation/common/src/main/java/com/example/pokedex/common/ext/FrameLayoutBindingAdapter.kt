package com.example.pokedex.common.ext

import android.widget.FrameLayout
import androidx.databinding.BindingAdapter

object FrameLayoutBindingAdapter {
    @BindingAdapter("widthDp", "heightDp", requireAll = true)
    @JvmStatic
    fun FrameLayout.setLayoutParams(width: Int?, height: Int?) {
        width ?: return
        height ?: return
        layoutParams = FrameLayout.LayoutParams(width.toPx(), height.toPx())
    }
}