package com.soumya.wwdablu.bitbeauty

import android.graphics.Bitmap

/**
 * Created by soumya on 3/23/18.
 */

class BitBeautyBitmap {

    private var mBitmap:Bitmap? = null

    /**
     * Returns the bitmap referred by the BitBeautyBitmap object
     * @return Bitmap? The actual bitmap or null
     */
    fun getBitmap() : Bitmap? {
        return mBitmap;
    }
}