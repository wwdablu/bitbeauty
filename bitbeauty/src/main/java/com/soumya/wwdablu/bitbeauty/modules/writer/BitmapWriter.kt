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
class BitmapWriter {

    enum class Format {
        PNG,
        JPEG,
        WEBP
    }

    fun write(bitBeautyBitmap: BitBeautyBitmap, bitmapFile:File, format:Format, quality:Int) : Observable<File> {

        return Observable.create { emitter ->
            val bitmapOutputStream = FileOutputStream(bitmapFile)
            val compressFormat = when(format) {

                BitmapWriter.Format.PNG -> Bitmap.CompressFormat.PNG
                BitmapWriter.Format.JPEG -> Bitmap.CompressFormat.JPEG
                BitmapWriter.Format.WEBP -> Bitmap.CompressFormat.WEBP
            }

            bitBeautyBitmap.getBitmap()!!.compress(compressFormat, if (quality <= 0) 1 else quality, bitmapOutputStream)
            bitmapOutputStream.close()

            emitter.onNext(bitmapFile)
            emitter.onComplete()
        }
    }
}