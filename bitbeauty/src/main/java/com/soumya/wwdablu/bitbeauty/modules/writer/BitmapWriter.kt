package com.soumya.wwdablu.bitbeauty.modules.writer

import android.graphics.Bitmap
import androidx.annotation.IntRange
import com.soumya.wwdablu.bitbeauty.BitBeautyBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

/**
 * Created by soumya on 3/26/18.
 */
class BitmapWriter internal constructor() {

    suspend fun write(bitBeautyBitmap: BitBeautyBitmap, bitmapFile:File,
                      format: Bitmap.CompressFormat,
                      @IntRange(from = 1, to = 100) quality:Int) {

        withContext(Dispatchers.IO) {
            runCatching {
                FileOutputStream(bitmapFile).use {
                    bitBeautyBitmap.getBitmap().compress(format,
                        if(1 >= quality) 1 else if (100 <= quality) 100 else quality, it)
                }
            }
        }
    }
}