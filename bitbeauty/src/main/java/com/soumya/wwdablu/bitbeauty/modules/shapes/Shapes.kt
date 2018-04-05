package com.soumya.wwdablu.bitbeauty.modules.shapes

import android.graphics.*
import android.support.annotation.ColorInt
import com.soumya.wwdablu.bitbeauty.BitBeautyBitmap

class Shapes {

    fun drawCircle(bitBeautyBitmap: BitBeautyBitmap, @ColorInt colorInt: Int, radius: Float, center: Point) {

        if(0F >= radius) return

        val canvas = Canvas(bitBeautyBitmap.getBitmap())
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = colorInt

        canvas.drawCircle(center.x.toFloat(), center.y.toFloat(), radius, paint)
    }

    fun drawOval(bitBeautyBitmap: BitBeautyBitmap, @ColorInt colorInt: Int, rect: RectF) {

        val canvas = Canvas(bitBeautyBitmap.getBitmap())
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = colorInt

        canvas.drawOval(rect, paint)
    }

    fun drawRect(bitBeautyBitmap: BitBeautyBitmap, @ColorInt colorInt: Int, rect: RectF) {

        val canvas = Canvas(bitBeautyBitmap.getBitmap())
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = colorInt

        canvas.drawRect(rect, paint)
    }

    fun freeform(bitBeautyBitmap: BitBeautyBitmap, @ColorInt colorInt: Int, path: Path) {

        val canvas = Canvas(bitBeautyBitmap.getBitmap())
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = colorInt

        canvas.drawPath(path, paint)
    }

    internal companion object {
        private val mInstance: Shapes = Shapes()
        internal fun getInstance(): Shapes {
            return mInstance
        }
    }
}