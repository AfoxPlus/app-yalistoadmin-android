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
    val styleText = Paint().apply {
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
        textSize = 15F
        color = Color.BLACK
    }

    val myPageInfo: PdfDocument.PageInfo? =
        PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create()

    val myPage: PdfDocument.Page = pdfDocument.startPage(myPageInfo)

    val canvas: Canvas = myPage.canvas

    val xPosition = 10F

    canvas.drawText(order.restaurant.uppercase(), pageWidth / 2.8F, 80F, styleText)
    canvas.drawLine(0F, 100F, pageWidth * 1F, 100F, paint)
    canvas.drawText("ORDEN: ${order.number}", pageWidth / 2.8F, 120F, styleText)
    canvas.drawLine(0F, 130F, pageWidth * 1F, 130F, paint)
    canvas.drawText("CLIENTE: ${order.client.name.uppercase()}                   ", xPosition, 160F, styleText)
    canvas.drawText("FECHA:  ${order.date}                           ", xPosition, 180F, styleText)
    if (order.orderType.code == "DELI") {
        canvas.drawText("${order.orderType.title.uppercase()}                                    ", xPosition, 200F, styleText)
    } else {
        canvas.drawText("MESA:   ${order.orderType.description}          ", xPosition, 200F, styleText)
    }
    canvas.drawLine(0F, 220F, pageWidth * 1F, 220F, paint)
    canvas.drawText("CANT.     DESCRIPCION", xPosition, 240F, styleText)
    canvas.drawLine(0F, 250F, pageWidth * 1F, 250F, paint)

    var positionY = 260F
    order.detail.forEach {
        positionY += 20F
        canvas.drawText(
            "${it.quantity} ${if (it.isMenu()) "(M) " else "  -  "} ${it.title.uppercase()}              ",
            xPosition,
            positionY,
            styleText
        )
        it.notes?.let { notes ->
            positionY += 20F
            canvas.drawText(
                notes.uppercase(),
                xPosition,
                positionY,
                styleText
            )
        }

        it.subDetail.forEach { detail ->
            positionY += 20F
            canvas.drawText("${it.quantity}         ${detail.title.uppercase()}", xPosition, positionY, styleText)
        }
    }
    positionY += 20F
    canvas.drawLine(0F, positionY, pageWidth * 1F, positionY, paint)

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
