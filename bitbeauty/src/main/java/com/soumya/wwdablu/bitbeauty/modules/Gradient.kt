package com.soumya.wwdablu.bitbeauty.modules

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RadialGradient
import android.graphics.Shader
import android.support.annotation.ColorInt
import com.soumya.wwdablu.bitbeauty.BitBeautyBitmap

/**
 * Created by soumya on 3/24/18.
 */
internal class Gradient {

    @Synchronized
    fun circularRadialGradient(bitBeautyBitmap: BitBeautyBitmap, cx:Float, cy:Float,
                       radius:Float, dither:Boolean, @ColorInt startColor:Int, @ColorInt endColor:Int) {

        val radialGradient = RadialGradient(cx, cy, radius, startColor, endColor, Shader.TileMode.CLAMP)
        circularRadialGradient(radialGradient, bitBeautyBitmap, cx, cy, radius, dither)
    }

    @Synchronized
    fun circularRadialGradient(bitBeautyBitmap: BitBeautyBitmap, cx:Float, cy:Float,
                   radius:Float, dither:Boolean, @ColorInt colorArray: IntArray, stopArray: FloatArray?) {
        val radialGradient = RadialGradient(cx, cy, radius, colorArray, stopArray, Shader.TileMode.CLAMP)
        circularRadialGradient(radialGradient, bitBeautyBitmap, cx, cy, radius, dither)
    }

    private fun circularRadialGradient(shader: Shader, bitBeautyBitmap: BitBeautyBitmap, cx:Float, cy:Float,
                       radius:Float, dither:Boolean) {

        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.isDither = dither
        paint.shader = shader

        val canvas = Canvas(bitBeautyBitmap.getBitmap())
        canvas.drawCircle(cx, cy, radius, paint)
    }

    companion object {
        private val mInstance:Gradient = Gradient()
        internal fun getInstance() : Gradient {
            return mInstance
        }
    }
}