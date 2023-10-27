package com.afoxplus.yalistoadmin.commons.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.widget.Toast
import com.afoxplus.yalistoadmin.domain.entities.Order
import java.io.File
import java.io.FileOutputStream

fun Context.generateOrderPDF(order: Order): String {
    val pageHeight = 380 + order.calculateDetailSize() * 20
    val pageWidth = 300
    val pdfDocument = PdfDocument()

    val paint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 1F
    }
    val title = Paint().apply {
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
        textSize = 15F
        color = Color.BLACK
    }

    val myPageInfo: PdfDocument.PageInfo? =
        PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create()

    val myPage: PdfDocument.Page = pdfDocument.startPage(myPageInfo)

    val canvas: Canvas = myPage.canvas

    val xPosition = 10F

    canvas.drawText(order.restaurant, pageWidth / 2.8F, 80F, title)
    canvas.drawLine(0F, 100F, pageWidth * 1F, 100F, paint)
    canvas.drawText("Order Detail", pageWidth / 2.8F, 120F, title)
    canvas.drawLine(0F, 140F, pageWidth * 1F, 140F, paint)
    canvas.drawText("Cliente: ${order.client.name}                   ", xPosition, 160F, title)
    canvas.drawText("Fecha:  ${order.date}                           ", xPosition, 180F, title)
    if (order.orderType.code == "DELI") {
        canvas.drawText("Delivery                                    ", xPosition, 200F, title)
    } else {
        canvas.drawText("Mesa:   ${order.orderType.description}          ", xPosition, 200F, title)
    }
    canvas.drawLine(0F, 220F, pageWidth * 1F, 220F, paint)
    canvas.drawText("Cant.  Descripcion", xPosition, 260F, title)

    var positionY = 280F
    order.detail.forEach {
        positionY += 20F
        canvas.drawText(
            "${it.quantity} ${if (it.isMenu()) "(M) " else "      "} ${it.title}              ",
            xPosition,
            positionY,
            title
        )
        it.subDetail.forEach { detail ->
            positionY += 20F
            canvas.drawText("${it.quantity}         ${detail.title}", xPosition, positionY, title)
        }
    }

    pdfDocument.finishPage(myPage)

    val file = File(externalCacheDir.toString(), "OrderDetail.pdf")

    try {
        pdfDocument.writeTo(FileOutputStream(file))
    } catch (e: Exception) {
        e.printStackTrace()
        Toast.makeText(this, "Fail to generate PDF file..", Toast.LENGTH_SHORT)
            .show()
    }
    pdfDocument.close()
    return file.absolutePath
}
