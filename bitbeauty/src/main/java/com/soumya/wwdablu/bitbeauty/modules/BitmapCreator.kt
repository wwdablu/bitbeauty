package com.soumya.wwdablu.bitbeauty.modules

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.support.annotation.ColorInt
import com.bumptech.glide.Glide
import com.soumya.wwdablu.bitbeauty.BitBeautyBitmap

/**
 * Created by soumya on 3/24/18.
 */
internal class BitmapCreator {

    @Synchronized
    fun createBitmapRGB(context: Context, width:Int, height:Int, @ColorInt color:Int) : BitBeautyBitmap? {

        val bmp:Bitmap = Glide.get(context).bitmapPool.get(width, height, Bitmap.Config.RGB_565)
        bmp.setHasAlpha(false)

        val canvas:Canvas? = Canvas()
        canvas?.setBitmap(bmp)
        canvas?.drawColor(color)

        return BitBeautyBitmap(bmp)
    }

    fun createBitmapARGB(context: Context, width: Int, height: Int, @ColorInt color: Int, config:Bitmap.Config) : BitBeautyBitmap? {

        val bmp:Bitmap = Glide.get(context).bitmapPool.get(width, height, config)
        bmp.setHasAlpha(true)

        val canvas:Canvas? = Canvas()
        canvas?.setBitmap(bmp)
        canvas?.drawColor(color)

        return BitBeautyBitmap(bmp)
    }

    companion object {
        private var mInstance:BitmapCreator = BitmapCreator()

        internal fun getInstance() : BitmapCreator {
            return mInstance
        }
    }
}