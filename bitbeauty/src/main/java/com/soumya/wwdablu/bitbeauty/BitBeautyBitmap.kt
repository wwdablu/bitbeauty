package com.soumya.wwdablu.bitbeauty

import android.graphics.Bitmap

/**
 * Created by soumya on 3/23/18.
 */

class BitBeautyBitmap internal constructor(bitmap: Bitmap) {

    private var mBitmap:Bitmap = bitmap
    private var mCanRecycleBitmap = false
    private var mBitmapId:String = ""

    /**
     * Returns the bitmap referred by the BitBeautyBitmap object
     * @return Bitmap? The actual bitmap or null
     */
    fun getBitmap() : Bitmap? {
        return if (mBitmap.isRecycled) null else mBitmap
    }

    fun getIdentifier() : String {
        return mBitmapId
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
}