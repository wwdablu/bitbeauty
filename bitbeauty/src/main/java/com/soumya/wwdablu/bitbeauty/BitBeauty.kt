package com.soumya.wwdablu.bitbeauty

import com.soumya.wwdablu.bitbeauty.modules.BitmapCreator

/**
 * Created by soumya on 3/23/18.
 */
class BitBeauty {

    fun createBitmapRGB(width: Int, height: Int) : BitBeautyBitmap? {
        return createBitmapRGB(width, height, 255, 255, 255)
    }

    fun createBitmapRGB(width: Int, height: Int, r: Int, g:Int, b:Int) : BitBeautyBitmap? {
        BitmapCreator().createBitmapRGB(r,g,b)
        return null
    }

    fun createBitmapRGBA(width: Int, height: Int) : BitBeautyBitmap? {
        return createBitmapRGBA(width, height, 255, 255, 255, 1F)
    }

    fun createBitmapRGBA(width: Int, height: Int, r:Int, g:Int, b:Int, a:Float) : BitBeautyBitmap? {
        return null
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