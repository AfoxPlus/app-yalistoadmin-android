package com.afoxplus.yalistoadmin.commons.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

private fun Context.saveImage(bitmap: Bitmap): Uri {
    val path: String = externalCacheDir.toString() + "/yalistoorder.jpg"
    var out: OutputStream?
    val file = File(path)
    try {
        out = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
        out.flush()
        out.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return FileProvider.getUriForFile(
        this,
        "$packageName.com.afoxplus.yalistoadmin.provider",
        file
    )
}

fun Context.shareBitmap(text: String, bitmap: Bitmap) {
    val imageUri: Uri = saveImage(bitmap)
    val chooserIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_STREAM, imageUri)
        type = "image/jpeg"
        putExtra(Intent.EXTRA_TEXT, text)
    }
    val shareIntent = Intent.createChooser(chooserIntent, null)
    startActivity(shareIntent)
}
