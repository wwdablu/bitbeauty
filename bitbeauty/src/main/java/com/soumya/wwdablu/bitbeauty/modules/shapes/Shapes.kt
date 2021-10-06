package com.soumya.wwdablu.bitbeauty.modules.shapes

import android.graphics.*
import androidx.annotation.ColorInt
import com.soumya.wwdablu.bitbeauty.BitBeautyBitmap

class Shapes internal constructor() {

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

    fun drawLine(bitBeautyBitmap: BitBeautyBitmap, @ColorInt colorInt: Int, lineWidth: Float = 4F, start: PointF, end: PointF) {

        val canvas = Canvas(bitBeautyBitmap.getBitmap())
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = colorInt
        paint.strokeWidth = lineWidth

        canvas.drawLine(start.x, start.y, end.x, end.y, paint)
    }

    fun drawPolygon(bitBeautyBitmap: BitBeautyBitmap, @ColorInt colorInt: Int, x: Float, y: Float, radius: Float, sides: Int) {

        val section = 2.0 * Math.PI / sides

        val path = Path()
        path.reset()
        path.moveTo((x + radius * Math.cos(0.0)).toFloat(), (y + radius * Math.sin(0.0)).toFloat())

        for (i in 1 until sides) {
            path.lineTo((x + radius * Math.cos(section * i)).toFloat(),
                (y + radius * Math.sin(section * i)).toFloat())
        }

        path.close()
        freeform(bitBeautyBitmap, colorInt, path)
    }

    fun freeform(bitBeautyBitmap: BitBeautyBitmap, @ColorInt colorInt: Int, path: Path) {

        val canvas = Canvas(bitBeautyBitmap.getBitmap())
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = colorInt

        canvas.drawPath(path, paint)
    }
}