package com.soumya.wwdablu.bitbeauty

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.support.annotation.ColorInt
import com.soumya.wwdablu.bitbeauty.modules.Creator
import com.soumya.wwdablu.bitbeauty.modules.gradient.Gradient
import com.soumya.wwdablu.bitbeauty.modules.gradient.LinearGradient
import com.soumya.wwdablu.bitbeauty.modules.gradient.RadialGradient

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


    /**
     * Erase the content displayed on the bitmap with the provided color.
     */
    fun erase(bitBeautyBitmap: BitBeautyBitmap, @ColorInt withColor:Int) {
        Creator.getInstance().erase(bitBeautyBitmap, withColor)
    }

    /**
     * Creates a circular radial gradient using two colors on a BitBeautyBitmap.
     * @param cx X coordinate of the center of the circle
     * @param cy Y coordinate of the center of the circle
     */
    fun radialGradientCircle(bitBeautyBitmap: BitBeautyBitmap, cx:Float, cy:Float,
                             radius:Float, dither:Boolean, @ColorInt startColor:Int, @ColorInt endColor:Int) {

        val colorArray = IntArray(2)
        colorArray[0] = startColor
        colorArray[1] = endColor
        RadialGradient.getInstance().drawCircle(bitBeautyBitmap, cx, cy, radius, dither, colorArray, null, Gradient.Mode.CLAMP)
    }

    /**
     * Creates a circular radial gradient using two colors on a BitBeautyBitmap.
     * @param cx X coordinate of the center of the circle
     * @param cy Y coordinate of the center of the circle
     */
    fun radialGradientCircle(bitBeautyBitmap: BitBeautyBitmap, cx:Float, cy:Float,
                             radius:Float, dither:Boolean, @ColorInt colorArray: IntArray, stopArray: FloatArray?,
                             mode:Gradient.Mode) {
        RadialGradient.getInstance().drawCircle(bitBeautyBitmap, cx, cy, radius, dither, colorArray, stopArray, mode)
    }

    /**
     *
     */
    fun radialGradientCircle(bitBeautyBitmap: BitBeautyBitmap, circleX: Float, circleY: Float, radialX: Float,
                             radialY: Float, radius: Float, dither:Boolean, @ColorInt colorArray: IntArray,
                             stepArray: FloatArray?, mode: Gradient.Mode) {
        RadialGradient.getInstance().drawCircle(bitBeautyBitmap, circleX, circleY, radialX, radialY, radius, dither, colorArray, stepArray, mode)
    }

    /**
     *
     */
    fun radialGradientOval(bitBeautyBitmap: BitBeautyBitmap, left: Float, top: Float, right:Float, bottom:Float,
                 dither:Boolean, @ColorInt colorArray: IntArray, stepArray: FloatArray?, mode: Gradient.Mode) {
        RadialGradient.getInstance().drawOval(bitBeautyBitmap, left, top, right, bottom, dither, colorArray, stepArray, mode)
    }

    /**
     *
     */
    fun linearGradientRect(bitBeautyBitmap: BitBeautyBitmap, startX:Float, startY:Float, endX:Float, endY:Float,
                           @ColorInt colorArray: IntArray, stepArray: FloatArray?, mode:Gradient.Mode) {

        LinearGradient.getInstance().drawRect(bitBeautyBitmap, startX, startY, endX, endY, colorArray, stepArray, mode)
    }

    /**
     *
     */
    fun linearGradientRect(bitBeautyBitmap: BitBeautyBitmap, rectStartX:Float, rectStartY:Float, rectEndX:Float, rectEndY:Float,
                       gradStartX:Float, gradStartY:Float, gradEndX:Float, gradEndY:Float,
                       @ColorInt colorArray: IntArray, stepArray: FloatArray?, mode:Gradient.Mode) {
        LinearGradient.getInstance().drawRect(bitBeautyBitmap, rectStartX, rectStartY, rectEndX, rectEndY,
                gradStartX, gradStartY, gradEndX, gradEndY, colorArray, stepArray, mode)
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