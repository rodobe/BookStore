package com.example.bookstore.extensions

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.palette.graphics.Palette
import com.example.bookstore.R

inline fun Context.getContrastColor(palette: Palette?): Int{
    val contrast = ColorUtils.calculateContrast(palette?.dominantSwatch?.rgb ?: ContextCompat.getColor(this, R.color.black), ContextCompat.getColor(this, R.color.white))
    return if (contrast > 1.5f) {
        ContextCompat.getColor(this, R.color.white)
    } else {
        ContextCompat.getColor(this, R.color.black)
    }
}