package com.soumya.wwdablu.bitbeauty

import android.content.Context
import android.graphics.Bitmap
import android.support.annotation.ColorInt

/**
 * Created by soumya on 3/23/18.
 */

class BitBeautyBitmap internal constructor(bitmap: Bitmap, config: Bitmap.Config) {

    private val mBitmap:Bitmap = bitmap
    private val mBitmapConfig = config

    private var mCanRecycleBitmap = false
    private var mBitmapId:String = ""

    val width:Int
    get() = mBitmap.width

    val height:Int
    get() = mBitmap.height

    val hasAlpha:Boolean
    get() = mBitmap.hasAlpha()

    fun getIdentifier() : String {
        return mBitmapId
    }

    fun getBitmap() : Bitmap? {
        return mBitmap
    }

    fun erase(@ColorInt color:Int) {
        BitBeauty.Editor.erase(this, color)
    }

    fun clone(context: Context): BitBeautyBitmap? {
        return BitBeauty.Editor.clone(context, this)
    }

    /**
     * Allows assigning an identifier to this bitmap so that it is easier to understand where
     * the bitmap is being used. Mainly used during development purpose. Maximum length of the
     * identifier can be 50 characters
     */
    fun setIdentifier(identifier:String) {

        var correctedIdentifier = identifier
        if(identifier.length >= 50) {
            correctedIdentifier = identifier.substring(0, 49)
        }
        mBitmapId = correctedIdentifier
    }

    /*
     * Internal methods which are only available for the module and not to outside world
     */

    internal fun canRecycle(boolean: Boolean) {
        mCanRecycleBitmap = boolean
    }

    internal fun getBitmapConfig(): Bitmap.Config {
        return mBitmapConfig
    }
}