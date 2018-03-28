package com.soumya.wwdablu.bitbeauty

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import com.soumya.wwdablu.bitbeauty.modules.Creator
import com.soumya.wwdablu.bitbeauty.modules.gradient.Gradient
import com.soumya.wwdablu.bitbeauty.modules.gradient.LinearGradient
import com.soumya.wwdablu.bitbeauty.modules.gradient.RadialGradient
import io.reactivex.Observable

/**
 * Created by soumya on 3/23/18.
 */
class BitBeauty {

    enum class ArgbFormat {
        ARG_4444,
        ARGB_8888
    }

    /**
     * Return an empty bitmap with the provide width and height. The bitmap will be created with a
     * default color of white. Note that you should not call recycle on the bitmap.
     */
    fun createBitmapRGB(context:Context, width: Int, height: Int) : BitBeautyBitmap? {
        return createBitmapRGB(context, width, height, Color.WHITE)
    }

    /**
     * Return an empty bitmap with the provide width, height and color of the bitmap. Note that you
     * should not call recycle on the bitmap.
     */
    fun createBitmapRGB(context:Context, width: Int, height: Int, @ColorInt color:Int) : BitBeautyBitmap? {
        return Creator.getInstance().createBitmapRGB(context, width, height, color)
    }

    /**
     * Returns a bitmap of the requested format of the provided width and height. The default format
     * of the bitmap will be ARGB_8888 format. Note that you should not call recycle on the bitmap.
     */
    fun createBitmapARGB(context:Context, width: Int, height: Int) : BitBeautyBitmap? {
        return createBitmapARGB(context, width, height, Color.TRANSPARENT, ArgbFormat.ARGB_8888)
    }

    /**
     * Returns a bitmap of the requested format of the provided width and height. Note that you
     * should not call recycle on the bitmap.
     */
    fun createBitmapARGB(context:Context, width: Int, height: Int, @ColorInt colorWithAlpha:Int, format:ArgbFormat) : BitBeautyBitmap? {

        var config = Bitmap.Config.ARGB_8888
        if(ArgbFormat.ARG_4444 == format) {
            config = Bitmap.Config.ARGB_4444
        }
        return Creator.getInstance().createBitmapARGB(context, width, height, colorWithAlpha, config)
    }

    fun createBitmap(context: Context, @DrawableRes image:Int) : Observable<BitBeautyBitmap> {
        return Creator.getInstance().createBitmap(context, image)
    }


    /**
     * Erase the content displayed on the bitmap with the provided color.
     */
    fun erase(bitBeautyBitmap: BitBeautyBitmap, @ColorInt withColor:Int) {
        Creator.getInstance().erase(bitBeautyBitmap, withColor)
    }

    /**
     * Returns a LinearGradient instance which can be used by the application
     */
    fun linearGradient() : LinearGradient {

        if(!::mLinearGradient.isInitialized) {
            mLinearGradient = LinearGradient()
        }

        return mLinearGradient
    }

    /**
     * Returns a Radian Gradient instance which can be used by the application
     */
    fun radialGradient() : RadialGradient {

        if(!::mRadialGradient.isInitialized) {
            mRadialGradient = RadialGradient()
        }

        return mRadialGradient
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

    private lateinit var mLinearGradient: LinearGradient
    private lateinit var mRadialGradient: RadialGradient
}