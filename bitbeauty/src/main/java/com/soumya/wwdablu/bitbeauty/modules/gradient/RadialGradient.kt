package com.soumya.wwdablu.bitbeauty.modules.gradient

import android.graphics.Canvas
import android.graphics.Paint
import android.support.annotation.ColorInt
import com.soumya.wwdablu.bitbeauty.BitBeautyBitmap
import android.graphics.RadialGradient as AndroidRadialGradient

/**
 * Created by soumya on 3/25/18.
 */
class RadialGradient private constructor() {

    internal companion object {
        private val mInstance: RadialGradient = RadialGradient()
        internal fun getInstance(): RadialGradient {
            return mInstance
        }
    }

    @Synchronized
    fun drawCircle(bitBeautyBitmap: BitBeautyBitmap, centerX: Float, centerY: Float, radius: Float,
               dither:Boolean, @ColorInt colorArray: IntArray, stepArray: FloatArray?, mode: Gradient.Mode) {

        drawCircle(bitBeautyBitmap, centerX, centerY, centerX, centerY, radius, dither, colorArray, stepArray, mode)
    }

    @Synchronized
    fun drawCircle(bitBeautyBitmap: BitBeautyBitmap, circleX: Float, circleY: Float, radialX: Float,
                   radialY: Float, radius: Float, dither:Boolean, @ColorInt colorArray: IntArray,
                   stepArray: FloatArray?, mode: Gradient.Mode) {

        val shader = createRadialGradient(radialX, radialY, radius, colorArray, stepArray, mode)

        val canvas = Canvas(bitBeautyBitmap.getBitmap())
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.shader = shader
        paint.isDither = dither
        canvas.drawCircle(circleX, circleY, radius, paint)
    }

    @Synchronized
    fun drawOval(bitBeautyBitmap: BitBeautyBitmap, left: Float, top: Float, right:Float, bottom:Float,
                 dither:Boolean, @ColorInt colorArray: IntArray, stepArray: FloatArray?, mode: Gradient.Mode) {

        val radius = if ((left+right)/2 > (top+bottom)/2) (left+right)/2 else (top+bottom)/2
        val shader = createRadialGradient((left+right)/2, (top+bottom)/2, radius, colorArray, stepArray, mode)

        val canvas = Canvas(bitBeautyBitmap.getBitmap())
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.shader = shader
        paint.isDither = dither
        canvas.drawOval(left, top, right, bottom, paint)
    }

    private fun createRadialGradient(centerX:Float, centerY:Float, radius:Float, @ColorInt colorArray: IntArray,
                     stepArray: FloatArray?, mode:Gradient.Mode) : AndroidRadialGradient {

        val shaderModel = Gradient.convertShaderMode(mode)
        return AndroidRadialGradient(centerX, centerY, radius, colorArray, stepArray, shaderModel)
    }
}