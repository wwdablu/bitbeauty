package com.soumya.wwdablu.bitbeauty.modules.effects

import android.graphics.*
import com.soumya.wwdablu.bitbeauty.BitBeautyBitmap

class Effects {

    @Synchronized
    fun grayscale(bitBeautyBitmap: BitBeautyBitmap) {

        grayscale(bitBeautyBitmap, Rect(0, 0, bitBeautyBitmap.getBitmap()?.width ?: 0,
                bitBeautyBitmap.getBitmap()?.height ?: 0))
    }

    fun grayscale(bitBeautyBitmap: BitBeautyBitmap, applyRect: Rect) {

        val canvas = Canvas(bitBeautyBitmap.getBitmap())
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)

        val colorMatrix = ColorMatrix()
        colorMatrix.setSaturation(0F)

        paint.colorFilter = ColorMatrixColorFilter(colorMatrix)
        canvas.drawBitmap(bitBeautyBitmap.getBitmap(), applyRect, applyRect, paint)
    }

    fun sepia(bitBeautyBitmap: BitBeautyBitmap) {

        sepia(bitBeautyBitmap, Rect(0, 0, bitBeautyBitmap.getBitmap()?.width ?: 0,
                bitBeautyBitmap.getBitmap()?.height ?: 0))
    }

    fun sepia(bitBeautyBitmap: BitBeautyBitmap, applyRect: Rect) {

        val canvas = Canvas(bitBeautyBitmap.getBitmap())
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)

        val colorMatrix = ColorMatrix()
        colorMatrix.setScale(1F, 1F, 0.8F, 1F)

        paint.colorFilter = ColorMatrixColorFilter(colorMatrix)
        canvas.drawBitmap(bitBeautyBitmap.getBitmap(), applyRect, applyRect, paint)
    }

    fun invert(bitBeautyBitmap: BitBeautyBitmap) {

        invert(bitBeautyBitmap, Rect(0, 0, bitBeautyBitmap.getBitmap()?.width ?: 0,
                bitBeautyBitmap.getBitmap()?.height ?: 0))
    }

    fun invert(bitBeautyBitmap: BitBeautyBitmap, applyRect: Rect) {

        val canvas = Canvas(bitBeautyBitmap.getBitmap())
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)

        val invertMatrix: FloatArray = floatArrayOf(
                -1F, 0F, 0F, 0F, 255F,
                0F, -1F, 0F, 0F, 255F,
                0F, 0F, -1F, 0F, 255F,
                0F, 0F, 0F, 1F, 0F
        )

        val colorMatrix = ColorMatrix(invertMatrix)

        paint.colorFilter = ColorMatrixColorFilter(colorMatrix)
        canvas.drawBitmap(bitBeautyBitmap.getBitmap(), applyRect, applyRect, paint)
    }

    internal companion object {
        private val mInstance: Effects = Effects()
        fun getInstance(): Effects {
            return mInstance
        }
    }
}