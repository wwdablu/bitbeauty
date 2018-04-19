package com.soumya.wwdablu.bitbeauty.modules.editor

import android.content.Context
import android.graphics.*
import android.support.annotation.ColorInt
import com.bumptech.glide.Glide
import com.soumya.wwdablu.bitbeauty.BitBeautyBitmap

class Editor {

    fun crop(context: Context, bitBeautyBitmap: BitBeautyBitmap, cropRect: Rect): BitBeautyBitmap? {

        if(bitBeautyBitmap.width == -1 || bitBeautyBitmap.height == -1 ||
                cropRect.width() > bitBeautyBitmap.width ||
                cropRect.height() > bitBeautyBitmap.height) {
            return bitBeautyBitmap
        }

        val croppedBitmap= Glide.get(context).bitmapPool.get(cropRect.width(),
                cropRect.height(), bitBeautyBitmap.getBitmapConfig())

        val canvas = Canvas(croppedBitmap)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        canvas.drawBitmap(bitBeautyBitmap.getBitmap(), cropRect, Rect(0,0,cropRect.right, cropRect.bottom), paint)

        return BitBeautyBitmap(croppedBitmap, bitBeautyBitmap.getBitmapConfig())
    }

    fun crop(context: Context, bitBeautyBitmap: BitBeautyBitmap, point: PointF, radius:Float): BitBeautyBitmap? {

        val w = bitBeautyBitmap.width
        val h = bitBeautyBitmap.height

        if(w == -1 || h == -1 || (radius * 2) > w || (radius * 2) > h) {
            return bitBeautyBitmap
        }

        val croppedBitmap= Glide.get(context).bitmapPool.get((radius * 2).toInt(),
                (radius * 2).toInt(), bitBeautyBitmap.getBitmapConfig())

        val canvas = Canvas(croppedBitmap)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        canvas.drawCircle(radius, radius, radius, paint)

        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

        val srcRect = Rect((point.x - radius).toInt(), (point.y - radius).toInt(), (point.x + radius).toInt(), (point.y + radius).toInt())
        val dstRect = Rect(0, 0, (radius * 2).toInt(), (radius * 2).toInt())
        canvas.drawBitmap(bitBeautyBitmap.getBitmap(), srcRect, dstRect, paint)

        return BitBeautyBitmap(croppedBitmap, bitBeautyBitmap.getBitmapConfig())
    }

    fun clone(context: Context, bitBeautyBitmap: BitBeautyBitmap): BitBeautyBitmap? {

        val bmp: Bitmap = Glide.get(context).bitmapPool.get(bitBeautyBitmap.width,
                bitBeautyBitmap.height, bitBeautyBitmap.getBitmapConfig())

        val canvas = Canvas(bmp)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        canvas.drawBitmap(bitBeautyBitmap.getBitmap(), 0F, 0F, paint)

        return BitBeautyBitmap(bmp, bitBeautyBitmap.getBitmapConfig())
    }

    fun copy(srcBitBeautyBitmap: BitBeautyBitmap, dstBitBeautyBitmap: BitBeautyBitmap) {

        val canvas = Canvas(dstBitBeautyBitmap.getBitmap())
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        erase(dstBitBeautyBitmap, Color.TRANSPARENT)
        canvas.drawBitmap(srcBitBeautyBitmap.getBitmap(), 0F, 0F, paint)
    }

    fun erase(bitBeautyBitmap: BitBeautyBitmap, @ColorInt withColor:Int) {
        (bitBeautyBitmap.getBitmap())?.eraseColor(withColor)
    }

    fun resize(context: Context, bitBeautyBitmap: BitBeautyBitmap, toWidth:Int, toHeight:Int,
               keepOriginal: Boolean = true): BitBeautyBitmap {

        val scaledBitmap = Bitmap.createScaledBitmap(bitBeautyBitmap.getBitmap(), toWidth, toHeight, false)
        //val scaledBitmap = Bitmap.createBitmap(bitBeautyBitmap.getBitmap(), 0, 0, )
        Glide.get(context).bitmapPool.put(scaledBitmap)

        var scaled = bitBeautyBitmap
        if(keepOriginal) {
            scaled = BitBeautyBitmap(scaledBitmap, bitBeautyBitmap.getBitmapConfig())
        } else {
            scaled.setBitmap(scaledBitmap)
        }

        return scaled
    }

    fun combine(srcBitBeautyBitmap: BitBeautyBitmap, dstBitBeautyBitmap: BitBeautyBitmap): BitBeautyBitmap {

        val srcWidth = srcBitBeautyBitmap.width
        val srcHeight = srcBitBeautyBitmap.height

        val dstWidth = dstBitBeautyBitmap.width
        val dstHeight = dstBitBeautyBitmap.height

        val startX = dstWidth - srcWidth
        val startY = dstHeight - srcHeight

        return combine(srcBitBeautyBitmap, dstBitBeautyBitmap, Point(startX, startY))
    }

    fun combine(srcBitBeautyBitmap: BitBeautyBitmap, dstBitBeautyBitmap: BitBeautyBitmap, anchor: Point): BitBeautyBitmap {

        val srcWidth = srcBitBeautyBitmap.width
        val srcHeight = srcBitBeautyBitmap.height

        val dstWidth = dstBitBeautyBitmap.width
        val dstHeight = dstBitBeautyBitmap.height

        //If source overlaps destination, then return source
        if(srcWidth >= dstWidth && srcHeight >= dstHeight) {
            return srcBitBeautyBitmap
        }

        val startX = anchor.x
        val startY = anchor.y

        val canvas = Canvas(dstBitBeautyBitmap.getBitmap())
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        canvas.drawBitmap(srcBitBeautyBitmap.getBitmap(), if(startX == 0) startX.toFloat() else (startX/2).toFloat(),
                if(startY == 0) startY.toFloat() else (startY/2).toFloat(), paint)

        return dstBitBeautyBitmap
    }

    fun mask(srcBitBeautyBitmap: BitBeautyBitmap, dstBitBeautyBitmap: BitBeautyBitmap, rectPoint: Point, mode: PorterDuff.Mode = PorterDuff.Mode.SRC_IN) {

        val width = dstBitBeautyBitmap.width
        val height = dstBitBeautyBitmap.height

        val canvas = Canvas(dstBitBeautyBitmap.getBitmap())
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.xfermode = PorterDuffXfermode(mode)

        val rect = Rect(rectPoint.x, rectPoint.y, rectPoint.x + width, rectPoint.y + height)
        canvas.drawBitmap(srcBitBeautyBitmap.getBitmap(), rect, Rect(0, 0, width, height), paint)
    }

    fun rotate(context: Context, bitBeautyBitmap: BitBeautyBitmap, degree:Float): BitBeautyBitmap? {

        val width = bitBeautyBitmap.width
        val height = bitBeautyBitmap.height

        if(width == -1 || height == -1) {
            return bitBeautyBitmap
        }

        val matrix = Matrix()
        matrix.postRotate(degree)

        val rotateBitmap = Bitmap.createBitmap(bitBeautyBitmap.getBitmap(), 0, 0,
                width, height, matrix, true)

        Glide.get(context).bitmapPool.put(rotateBitmap)

        return BitBeautyBitmap(rotateBitmap, bitBeautyBitmap.getBitmapConfig())
    }

    internal companion object {
        private val mInstance: Editor = Editor()
        internal fun getInstance(): Editor {
            return mInstance
        }
    }
}