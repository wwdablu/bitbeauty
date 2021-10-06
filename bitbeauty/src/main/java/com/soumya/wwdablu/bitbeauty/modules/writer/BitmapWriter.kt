package com.soumya.wwdablu.bitbeauty.modules.writer

import android.graphics.Bitmap
import com.soumya.wwdablu.bitbeauty.BitBeautyBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

/**
 * Created by soumya on 3/26/18.
 */
class BitmapWriter internal constructor() {

    enum class Format {
        PNG,
        JPEG
    }

    suspend fun write(bitBeautyBitmap: BitBeautyBitmap, bitmapFile:File, format:Format, quality:Int) {

        withContext(Dispatchers.IO) {

            FileOutputStream(bitmapFile).use {
                val compressFormat = when(format) {

                    Format.PNG -> Bitmap.CompressFormat.PNG
                    Format.JPEG -> Bitmap.CompressFormat.JPEG
                }

                bitBeautyBitmap.getBitmap().compress(compressFormat,
                    if(1 >= quality) 1 else if (100 <= quality) 100 else quality, it)
            }
        }
    }
}