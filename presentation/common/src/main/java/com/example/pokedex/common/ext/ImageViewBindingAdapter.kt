package com.example.pokedex.common.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.pokedex.common.R

object ImageViewBindingAdapter {

    @BindingAdapter("type_half_circle")
    @JvmStatic
    fun ImageView.setTypeHalfCircle(typeName: String?) {
        if (typeName?.isNotEmpty() == true) {
            this.setImageResource(
                when (typeName) {
                    "normal" -> R.drawable.ic_normal_color_half_circle
                    "fire" -> R.drawable.ic_fire_color_half_circle
                    "water" -> R.drawable.ic_water_color_half_circle
                    "grass" -> R.drawable.ic_grass_color_half_circle
                    "electric" -> R.drawable.ic_electric_color_half_circle
                    "ice" -> R.drawable.ic_ice_color_half_circle
                    "fighting" -> R.drawable.ic_fighting_color_half_circle
                    "poison" -> R.drawable.ic_poison_color_half_circle
                    "ground" -> R.drawable.ic_ground_color_half_circle
                    "flying" -> R.drawable.ic_flying_color_half_circle
                    "psychic" -> R.drawable.ic_psychic_color_half_circle
                    "bug" -> R.drawable.ic_bug_color_half_circle
                    "rock" -> R.drawable.ic_rock_color_half_circle
                    "ghost" -> R.drawable.ic_ghost_color_half_circle
                    "dragon" -> R.drawable.ic_dragon_color_half_circle
                    "dark" -> R.drawable.ic_dark_color_half_circle
                    "steel" -> R.drawable.ic_steel_color_half_circle
                    "fairy" -> R.drawable.ic_fairy_color_half_circle
                    else -> R.drawable.ic_normal_color_half_circle
                }
            )
        }
    }
}