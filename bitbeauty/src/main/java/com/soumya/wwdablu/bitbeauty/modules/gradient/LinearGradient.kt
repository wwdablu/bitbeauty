package com.soumya.wwdablu.bitbeauty.modules.gradient

import android.graphics.Canvas
import android.graphics.Paint
import android.support.annotation.ColorInt
import com.soumya.wwdablu.bitbeauty.BitBeautyBitmap
import android.graphics.LinearGradient as AndroidLinearGradient

/**
 * Created by soumya on 3/25/18.
 */
class LinearGradient private constructor() {

    @Synchronized
    fun drawRect(bitBeautyBitmap: BitBeautyBitmap, startX:Float, startY:Float, endX:Float, endY:Float,
               @ColorInt colorArray: IntArray, stepArray: FloatArray?, mode:Gradient.Mode) {

        drawRect(bitBeautyBitmap, startX, startY, endX, endY, startX, startY, endX, endY, colorArray, stepArray, mode)
    }

    @Synchronized
    fun drawRect(bitBeautyBitmap: BitBeautyBitmap, rectStartX:Float, rectStartY:Float, rectEndX:Float,
                 rectEndY:Float, gradStartX:Float, gradStartY:Float, gradEndX:Float, gradEndY:Float,
                 @ColorInt colorArray: IntArray, stepArray: FloatArray?, mode:Gradient.Mode) {

        val shaderModel = Gradient.convertShaderMode(mode)
        val linearGradient = AndroidLinearGradient(gradStartX, gradStartY, gradEndX, gradEndY, colorArray, stepArray, shaderModel)

        val canvas = Canvas(bitBeautyBitmap.getBitmap())
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.shader = linearGradient
        canvas.drawRect(rectStartX, rectStartY, rectEndX, rectEndY, paint)
    }

    internal companion object {
        val mInstance: LinearGradient = LinearGradient()
        internal fun getInstance(): LinearGradient {
            return mInstance
        }
    }
}