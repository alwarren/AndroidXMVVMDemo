package com.ntxdroid.spacex.core.extension

//import com.ntxdroid.spacex.core.platform.materialColors
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import java.util.*


/**
 * Created by Al Warren on 1/25/2019.
 */

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)

fun View.invisible() { this.visibility = View.GONE }

fun Snackbar.setTextColor(colorId: Int): Snackbar {
    view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        ?.setTextColor(ContextCompat.getColor(context, colorId))
    return this
}

fun TextView.setRandomMaterialBackground() {
    val colors = arrayOf("#ff1744", "#E91E63", "#673AB7", "#3F51B5", "#3F51B5", "#2196F3",
        "#03A9F4", "#00BCD4", "#009688", "#4CAF50", "#8BC34A",
        "#CDDC39", "#FFC107", "#FF9800", "#FF5722")
    background.setColorFilter(Color.parseColor(colors[Random().nextInt(colors.size)]), PorterDuff.Mode.SRC_OVER)
}

fun View.colorList() = listOf("#d32f2f","#FF5722","#ffc400","#00c853","#00b0ff","#304ffe","#9900FF")