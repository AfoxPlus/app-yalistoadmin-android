package com.afoxplus.yalistoadmin.cross.utils

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import java.io.File

fun Context.sharePDF(path: String) {
    val file = File(path)
    val chooserIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(
            Intent.EXTRA_STREAM,
            FileProvider.getUriForFile(
                this@sharePDF,
                "com.afoxplus.yalistoadmin.provider.share.document",
                file
            )
        )
        type = "application/pdf"
        putExtra(Intent.EXTRA_TEXT, "Order: 001")
    }
    val shareIntent = Intent.createChooser(chooserIntent, null)
    startActivity(shareIntent)
}
