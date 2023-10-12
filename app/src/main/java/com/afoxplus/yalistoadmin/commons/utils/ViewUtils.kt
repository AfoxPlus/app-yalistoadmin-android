package com.afoxplus.yalistoadmin.commons.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View

fun View.generateBitmap(): Bitmap {
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    layout(left, top, right, bottom)
    draw(canvas)
    return bitmap
}
