package com.soumya.wwdablu.bitbeauty.modules.creator

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.soumya.wwdablu.bitbeauty.BitBeautyBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by soumya on 3/24/18.
 */
class Creator internal constructor() {

    fun createBitmap(context: Context, width:Int, height:Int, @ColorInt color:Int) : BitBeautyBitmap? {
        return createBitmap(context, width, height, color, Bitmap.Config.ARGB_8888)
    }

    fun createBitmap(context: Context, width: Int, height: Int, @ColorInt color: Int, config:Bitmap.Config) : BitBeautyBitmap? {

        val bmp:Bitmap = Glide.get(context).bitmapPool.get(width, height, config)
        bmp.setHasAlpha(true)

        val canvas:Canvas? = Canvas()
        canvas?.setBitmap(bmp)
        canvas?.drawColor(color)

        return BitBeautyBitmap(bmp, config)
    }

    fun createBitmapFromBitmap(context: Context, bitmap: Bitmap): BitBeautyBitmap? {

        val newBitmap = Glide.get(context).bitmapPool.get(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(newBitmap)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        canvas.drawBitmap(bitmap, 0F, 0F, paint)

        return BitBeautyBitmap(newBitmap, Bitmap.Config.ARGB_8888)
    }

    suspend fun createBitmapFromDrawable(context: Context, @DrawableRes image:Int) : BitBeautyBitmap {

        return withContext(Dispatchers.IO) {
            BitBeautyBitmap(Glide.with(context)
                .asBitmap()
                .load(image)
                .submit()
                .get(), Bitmap.Config.ARGB_8888)
        }
    }

    suspend fun createBitmapFromUrl(context: Context, url:String): BitBeautyBitmap {

        return withContext(Dispatchers.IO) {
            BitBeautyBitmap(Glide.with(context)
                .asBitmap()
                .load(url)
                .submit()
                .get(), Bitmap.Config.ARGB_8888)
        }
    }
}