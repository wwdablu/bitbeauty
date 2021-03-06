package com.soumya.wwdablu.bitbeauty.modules.writer

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.soumya.wwdablu.bitbeauty.BitBeautyBitmap
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import java.io.File
import java.io.FileOutputStream

/**
 * Created by soumya on 3/26/18.
 */
class BitmapWriter private constructor() {

    enum class Format {
        PNG,
        JPEG,
        WEBP
    }

    internal companion object {
        private val mInstance: BitmapWriter = BitmapWriter()
        fun getInstance(): BitmapWriter {
            return mInstance
        }
    }

    fun write(bitBeautyBitmap: BitBeautyBitmap, bitmapFile:File, format:Format, quality:Int) : Observable<File> {

        return Observable.create { emitter ->
            val bitmapOutputStream = FileOutputStream(bitmapFile)
            val compressFormat = when(format) {

                BitmapWriter.Format.PNG -> Bitmap.CompressFormat.PNG
                BitmapWriter.Format.JPEG -> Bitmap.CompressFormat.JPEG
                BitmapWriter.Format.WEBP -> Bitmap.CompressFormat.WEBP
            }

            bitBeautyBitmap.getBitmap()!!.compress(compressFormat,
                    if(1 >= quality) 1 else if (100 <= quality) 100 else quality, bitmapOutputStream)
            bitmapOutputStream.close()

            emitter.onNext(bitmapFile)
            emitter.onComplete()
        }
    }
}