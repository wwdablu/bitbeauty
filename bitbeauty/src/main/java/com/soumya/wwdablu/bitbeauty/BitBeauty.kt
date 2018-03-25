package com.soumya.wwdablu.bitbeauty

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.support.annotation.ColorInt
import com.soumya.wwdablu.bitbeauty.modules.BitmapCreator

/**
 * Created by soumya on 3/23/18.
 */
class BitBeauty {

    enum class ArgbFormat {
        ARG_4444,
        ARGB_8888
    }

    fun createBitmapRGB(context:Context, width: Int, height: Int) : BitBeautyBitmap? {
        return createBitmapRGB(context, width, height, Color.WHITE)
    }

    /**
     * Return an empty bitmap with the provide width, height and color of the bitmap. Note that you
     * should not call recycle on the bitmap.
     */
    fun createBitmapRGB(context:Context, width: Int, height: Int, @ColorInt color:Int) : BitBeautyBitmap? {
        return BitmapCreator.getInstance().createBitmapRGB(context, width, height, color)
    }

    fun createBitmapARGB(context:Context, width: Int, height: Int) : BitBeautyBitmap? {
        return createBitmapARGB(context, width, height, Color.TRANSPARENT, ArgbFormat.ARGB_8888)
    }

    fun createBitmapARGB(context:Context, width: Int, height: Int, @ColorInt colorWithAlpha:Int, format:ArgbFormat) : BitBeautyBitmap? {

        var config = Bitmap.Config.ARGB_8888
        if(ArgbFormat.ARG_4444 == format) {
            config = Bitmap.Config.ARGB_4444
        }
        return BitmapCreator.getInstance().createBitmapARGB(context, width, height, colorWithAlpha, config)
    }

    companion object {
        private var mInstance:BitBeauty = BitBeauty()

        @Synchronized
        fun getInstance() : BitBeauty {
            return mInstance
        }
    }

    private constructor() {
        mInstance = this
    }
}