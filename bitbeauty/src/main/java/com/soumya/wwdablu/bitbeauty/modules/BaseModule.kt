package com.soumya.wwdablu.bitbeauty.modules

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.Glide

abstract class BaseModule internal constructor() {

    internal fun getBitmap(context: Context, width: Int, height: Int,
                              config: Bitmap.Config = Bitmap.Config.ARGB_8888) : Bitmap {
        return Glide.get(context).bitmapPool.get(width, height, config)
    }

    internal fun putBitmap(context: Context, bitmap: Bitmap) {
        Glide.get(context).bitmapPool.put(bitmap)
    }
}