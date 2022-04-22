package com.soumya.wwdablu.bitbeauty.modules.creator

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.soumya.wwdablu.bitbeauty.BitBeautyBitmap
import com.soumya.wwdablu.bitbeauty.modules.BaseModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by soumya on 3/24/18.
 */
class Creator internal constructor() : BaseModule() {

    fun createBitmap(context: Context, width:Int, height:Int, @ColorInt color:Int) : BitBeautyBitmap {
        return createBitmap(context, width, height, color, Bitmap.Config.ARGB_8888)
    }

    fun createBitmap(context: Context, width: Int, height: Int, @ColorInt color: Int, config:Bitmap.Config) : BitBeautyBitmap {

        val bmp:Bitmap = getBitmap(context, width, height, config).apply {
            setHasAlpha(true)
        }

        Canvas().apply {
            setBitmap(bmp)
            drawColor(color)
        }

        return BitBeautyBitmap(bmp, config)
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