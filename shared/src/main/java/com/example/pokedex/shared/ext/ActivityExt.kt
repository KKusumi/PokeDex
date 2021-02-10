package com.example.pokedex.shared.ext

import android.app.Activity
import android.graphics.Color
import android.view.WindowManager

fun Activity.changeStatusBarColor(colorCode: String) {
    this.window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    this.window?.statusBarColor = Color.parseColor(colorCode)
}