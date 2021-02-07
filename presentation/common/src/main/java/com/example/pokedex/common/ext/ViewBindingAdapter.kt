package com.example.pokedex.common.ext

import android.view.View
import androidx.databinding.BindingAdapter

object ViewBindingAdapter {
    @BindingAdapter("status_bar_width")
    @JvmStatic
    fun View.setStatusBarWidth(param: Int?) {
        if (param != null) {
            val layoutParams = this.layoutParams
            /**
             * 本当は長さの計算式これじゃないけど、
             * デザイン日本語→実装英語なのでバーの長さいい感じに調整している。
             */
            layoutParams.width = ((128 * param) / 255) * 2
            this.layoutParams = layoutParams
        }
    }
}