package com.soumya.wwdablu.bitbeauty

import android.content.Context
import android.graphics.Bitmap
import androidx.annotation.ColorInt

/**
 * Created by soumya on 3/23/18.
 */

class BitBeautyBitmap internal constructor(bitmap: Bitmap, config: Bitmap.Config) {

    private var mBitmap:Bitmap = bitmap
    private val mBitmapConfig = config

    private var mBitmapId:String = ""

    var width:Int = mBitmap.width
    private set
    var height:Int = mBitmap.height
    private set

    val hasAlpha:Boolean = mBitmap.hasAlpha()

    fun getIdentifier() : String {
        return mBitmapId
    }

    fun getBitmap() : Bitmap {
        return mBitmap
    }

    fun erase(@ColorInt color:Int) {
        BitBeauty.Editor.erase(this, color)
    }

    fun clone(context: Context): BitBeautyBitmap? {
        return BitBeauty.Editor.clone(context, this)
    }

    fun resize(context: Context, toWidth:Int, toHeight:Int, keepOriginal: Boolean): BitBeautyBitmap {
        return BitBeauty.Editor.resize(context,this, toWidth, toHeight, keepOriginal)
    }

    fun rotate(context: Context, bitBeautyBitmap: BitBeautyBitmap, degree: Float): BitBeautyBitmap? {
        return BitBeauty.Editor.rotate(context, bitBeautyBitmap, degree)
    }

    fun setIdentifier(identifier:String) {
        mBitmapId = identifier
    }

    /*
     * Internal methods which are only available for the module and not to outside world
     */

    internal fun isValid() : Boolean {
        return !isRecycled() && width >= 1 && height >= 1
    }

    internal fun isInvalid() = !isValid()

    internal fun isRecycled() = mBitmap.isRecycled

    internal fun getBitmapConfig(): Bitmap.Config {
        return mBitmapConfig
    }

    internal fun setBitmap(bitmap: Bitmap) {
        width = bitmap.width
        height = bitmap.height
        mBitmap = bitmap
    }
}