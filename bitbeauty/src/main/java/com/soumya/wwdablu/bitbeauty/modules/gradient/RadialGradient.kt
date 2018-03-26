package com.soumya.wwdablu.bitbeauty.modules.gradient

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Shader
import android.support.annotation.ColorInt
import com.soumya.wwdablu.bitbeauty.BitBeautyBitmap

import android.graphics.RadialGradient as AndroidRadialGradient

/**
 * Created by soumya on 3/25/18.
 */
internal class RadialGradient {

    fun drawCircle(bitBeautyBitmap: BitBeautyBitmap, centerX: Float, centerY: Float, radius: Float,
               dither:Boolean, @ColorInt colorArray: IntArray, stepArray: FloatArray?, mode: Gradient.Mode) {

        val shader = createRadialGradient(centerX, centerY, radius, colorArray, stepArray, mode)

        val canvas = Canvas(bitBeautyBitmap.getBitmap())
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.shader = shader
        paint.isDither = dither
        canvas.drawCircle(centerX, centerY, radius, paint)
    }

    private fun createRadialGradient(centerX:Float, centerY:Float, radius:Float, @ColorInt colorArray: IntArray,
                     stepArray: FloatArray?, mode:Gradient.Mode) : AndroidRadialGradient {

        val shaderModel = when (mode) {
            Gradient.Mode.MIRROR -> Shader.TileMode.MIRROR
            Gradient.Mode.REPEAT -> Shader.TileMode.REPEAT
            Gradient.Mode.CLAMP -> Shader.TileMode.CLAMP
        }

        return AndroidRadialGradient(centerX, centerY, radius, colorArray, stepArray, shaderModel)
    }

    companion object {
        private val mInstances:RadialGradient = RadialGradient()
        internal fun getInstance() : RadialGradient {
            return mInstances
        }
    }
}