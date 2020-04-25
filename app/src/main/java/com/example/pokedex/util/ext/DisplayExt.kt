package com.example.pokedex.util.ext

import android.content.res.Resources

/**
 * Created by watanabe on 2018/05/25.
 */

fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()

fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()