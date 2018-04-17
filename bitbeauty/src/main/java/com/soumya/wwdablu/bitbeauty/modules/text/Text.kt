package com.soumya.wwdablu.bitbeauty.modules.text

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.support.annotation.ColorInt
import com.soumya.wwdablu.bitbeauty.BitBeautyBitmap

class Text {

    fun write(bitBeautyBitmap: BitBeautyBitmap, text: String, textSize: Float, @ColorInt color:Int, anchor: PointF) {

        val canvas = Canvas(bitBeautyBitmap.getBitmap())
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = color
        paint.textSize = textSize

        canvas.drawText(text, anchor.x, anchor.y, paint)
    }

    internal companion object {
        private val mInstance: Text = Text()
        internal fun getInstance(): Text {
            return mInstance
        }
    }
}