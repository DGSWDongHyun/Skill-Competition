package com.simple.module2_3.data.module

import android.content.Context
import android.graphics.Bitmap
import android.util.TypedValue
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix

object ZxingModule {
    private val WHITE : Int = 0xFFFFFFFF.toInt()
    private val BLACK : Int = 0xFF000000.toInt()

    fun createBarcode(code : String, context : Context) : Bitmap {
        val widthPx = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 390f,
            context.resources.displayMetrics
        )

        val heightPx = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 111f,
            context.resources.displayMetrics
        )

        val format : BarcodeFormat = BarcodeFormat.CODE_128
        val martix : BitMatrix = MultiFormatWriter().encode(code, format, widthPx.toInt(), heightPx.toInt())
        val bitmap = createBitmap(martix)

        return bitmap
    }

    fun createBitmap(martix : BitMatrix) : Bitmap {
        val width = martix.width
        val height = martix.height
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        for(x in 0 until width){
            for(y in 0 until height) {
                bitmap.setPixel(x,y, if(martix.get(x,y)) BLACK else WHITE)
            }
        }
        return bitmap
    }
}