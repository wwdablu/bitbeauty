package com.soumya.wwdablu.bitbeauty.modules.editor

import android.content.Context
import android.graphics.*
import android.support.annotation.ColorInt
import com.bumptech.glide.Glide
import com.soumya.wwdablu.bitbeauty.BitBeautyBitmap

class Editor {

    fun crop(context: Context, bitBeautyBitmap: BitBeautyBitmap, cropRect: Rect): BitBeautyBitmap? {

        val w = bitBeautyBitmap.getBitmap()?.width ?: -1
        val h = bitBeautyBitmap.getBitmap()?.height ?: -1

        if(w == -1 || h == -1 || cropRect.width() > w || cropRect.height() > h) {
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

        val w = bitBeautyBitmap.getBitmap()?.width ?: -1
        val h = bitBeautyBitmap.getBitmap()?.height ?: -1

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

        val bmp: Bitmap = Glide.get(context).bitmapPool.get(bitBeautyBitmap.getBitmap()?.width ?: 0,
                bitBeautyBitmap.getBitmap()?.height ?: 0, bitBeautyBitmap.getBitmapConfig())

        val canvas = Canvas(bmp)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        canvas.drawBitmap(bitBeautyBitmap.getBitmap(), 0F, 0F, paint)

        return BitBeautyBitmap(bmp, bitBeautyBitmap.getBitmapConfig())
    }

    fun erase(bitBeautyBitmap: BitBeautyBitmap, @ColorInt withColor:Int) {

        if(bitBeautyBitmap.getBitmap() == null) {
            return
        }

        Canvas(bitBeautyBitmap.getBitmap()).drawColor(withColor)
    }

    fun combine(srcBitBeautyBitmap: BitBeautyBitmap, dstBitBeautyBitmap: BitBeautyBitmap): BitBeautyBitmap {

        val srcWidth = srcBitBeautyBitmap.getBitmap()?.width ?: -1
        val srcHeight = srcBitBeautyBitmap.getBitmap()?.height ?: -1

        val dstWidth = dstBitBeautyBitmap.getBitmap()?.width ?: -1
        val dstHeight = dstBitBeautyBitmap.getBitmap()?.height ?: -1

        //If source overlaps destination, then return source
        if(srcWidth >= dstWidth && srcHeight >= dstHeight) {
            return srcBitBeautyBitmap
        }

        val startX = dstWidth - srcWidth
        val startY = dstHeight - srcHeight

        val canvas = Canvas(dstBitBeautyBitmap.getBitmap())
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        canvas.drawBitmap(srcBitBeautyBitmap.getBitmap(), if(startX == 0) startX.toFloat() else (startX/2).toFloat(),
                if(startY == 0) startY.toFloat() else (startY/2).toFloat(), paint)

        return dstBitBeautyBitmap
    }

    fun mask(srcBitBeautyBitmap: BitBeautyBitmap, dstBitBeautyBitmap: BitBeautyBitmap, mode: PorterDuff.Mode = PorterDuff.Mode.SRC_IN) {

        val canvas = Canvas(dstBitBeautyBitmap.getBitmap())
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.xfermode = PorterDuffXfermode(mode)

        canvas.drawBitmap(srcBitBeautyBitmap.getBitmap(), 0F, 0F, paint)
    }

    fun rotate(context: Context, bitBeautyBitmap: BitBeautyBitmap, degree:Float): BitBeautyBitmap? {

        val width = bitBeautyBitmap.getBitmap()?.width ?: -1
        val height = bitBeautyBitmap.getBitmap()?.height ?: -1

        if(width == -1 || height == -1) {
            return bitBeautyBitmap
        }

        val matrix = Matrix()
        matrix.postRotate(degree)

        val rotateBitmap = Bitmap.createBitmap(bitBeautyBitmap.getBitmap(), 0, 0,
                width, height, matrix, true)
        val managedRotateBitmap = Glide.get(context).bitmapPool.get(rotateBitmap.width,
                rotateBitmap.height, bitBeautyBitmap.getBitmapConfig())
        val canvas = Canvas(managedRotateBitmap)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        canvas.drawBitmap(rotateBitmap, 0F, 0F, paint)
        rotateBitmap.recycle()

        return BitBeautyBitmap(managedRotateBitmap, bitBeautyBitmap.getBitmapConfig())
    }

    internal companion object {
        private val mInstance: Editor = Editor()
        internal fun getInstance(): Editor {
            return mInstance
        }
    }
}