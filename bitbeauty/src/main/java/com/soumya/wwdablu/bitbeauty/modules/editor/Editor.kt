package com.soumya.wwdablu.bitbeauty.modules.editor

import android.content.Context
import android.graphics.*
import android.support.annotation.ColorInt
import com.bumptech.glide.Glide
import com.soumya.wwdablu.bitbeauty.BitBeautyBitmap

class Editor {

    @Synchronized
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
        canvas.drawBitmap(bitBeautyBitmap.getBitmap(), point.x, point.y, paint)

        return BitBeautyBitmap(croppedBitmap, bitBeautyBitmap.getBitmapConfig())
    }

    @Synchronized
    fun clone(context: Context, bitBeautyBitmap: BitBeautyBitmap): BitBeautyBitmap? {

        val bmp: Bitmap = Glide.get(context).bitmapPool.get(bitBeautyBitmap.getBitmap()?.width ?: 0,
                bitBeautyBitmap.getBitmap()?.height ?: 0, bitBeautyBitmap.getBitmapConfig())

        val canvas = Canvas(bmp)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        canvas.drawBitmap(bitBeautyBitmap.getBitmap(), 0F, 0F, paint)

        return BitBeautyBitmap(bmp, bitBeautyBitmap.getBitmapConfig())
    }

    @Synchronized
    fun erase(bitBeautyBitmap: BitBeautyBitmap, @ColorInt withColor:Int) {

        if(bitBeautyBitmap.getBitmap() == null) {
            return
        }

        Canvas(bitBeautyBitmap.getBitmap()).drawColor(withColor)
    }

    internal companion object {
        private val mInstance: Editor = Editor()
        internal fun getInstance(): Editor {
            return mInstance
        }
    }
}