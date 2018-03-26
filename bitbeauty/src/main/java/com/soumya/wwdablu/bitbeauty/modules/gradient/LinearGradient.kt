package com.soumya.wwdablu.bitbeauty.modules.gradient

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Shader
import android.support.annotation.ColorInt
import com.soumya.wwdablu.bitbeauty.BitBeautyBitmap

import android.graphics.LinearGradient as AndroidLinearGradient

/**
 * Created by soumya on 3/25/18.
 */
internal class LinearGradient {

    @Synchronized
    fun drawRect(bitBeautyBitmap: BitBeautyBitmap, startX:Float, startY:Float, endX:Float, endY:Float,
               @ColorInt colorArray: IntArray, stepArray: FloatArray?, mode:Gradient.Mode) {

        val shaderModel = when (mode) {
            Gradient.Mode.MIRROR -> Shader.TileMode.MIRROR
            Gradient.Mode.REPEAT -> Shader.TileMode.REPEAT
            Gradient.Mode.CLAMP -> Shader.TileMode.CLAMP
        }

        val linearGradient = AndroidLinearGradient(startX, startY, endX, endY, colorArray, stepArray, shaderModel)

        val canvas = Canvas(bitBeautyBitmap.getBitmap())
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.shader = linearGradient
        canvas.drawRect(startX, startY, endX, endY, paint)
    }

    companion object {
        private val mInstance:LinearGradient = LinearGradient()
        internal fun getInstance() : LinearGradient {
            return mInstance
        }
    }
}