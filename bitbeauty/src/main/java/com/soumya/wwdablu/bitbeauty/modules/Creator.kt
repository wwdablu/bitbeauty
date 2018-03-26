package com.soumya.wwdablu.bitbeauty.modules

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.soumya.wwdablu.bitbeauty.BitBeautyBitmap
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.schedulers.Schedulers

/**
 * Created by soumya on 3/24/18.
 */
internal class Creator {

    @Synchronized
    fun createBitmapRGB(context: Context, width:Int, height:Int, @ColorInt color:Int) : BitBeautyBitmap? {

        val bmp:Bitmap = Glide.get(context).bitmapPool.get(width, height, Bitmap.Config.RGB_565)
        bmp.setHasAlpha(false)

        val canvas:Canvas? = Canvas()
        canvas?.setBitmap(bmp)
        canvas?.drawColor(color)

        return BitBeautyBitmap(bmp)
    }

    @Synchronized
    fun createBitmapARGB(context: Context, width: Int, height: Int, @ColorInt color: Int, config:Bitmap.Config) : BitBeautyBitmap? {

        val bmp:Bitmap = Glide.get(context).bitmapPool.get(width, height, config)
        bmp.setHasAlpha(true)

        val canvas:Canvas? = Canvas()
        canvas?.setBitmap(bmp)
        canvas?.drawColor(color)

        return BitBeautyBitmap(bmp)
    }

    fun createBitmap(context: Context, @DrawableRes image:Int) : Observable<BitBeautyBitmap> {

        return Observable.create(ObservableOnSubscribe<BitBeautyBitmap> { emitter ->
            val simpleTarget = object : SimpleTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {

                    val bitBeautyBitmap = BitBeautyBitmap(resource)
                    emitter.onNext(bitBeautyBitmap)
                }
            }
            Glide.with(context).asBitmap().load(image).into(simpleTarget)
        }).subscribeOn(Schedulers.io())
    }

    @Synchronized
    fun erase(bitBeautyBitmap: BitBeautyBitmap, @ColorInt withColor:Int) {

        if(bitBeautyBitmap.getBitmap() == null) {
            return
        }

        Canvas(bitBeautyBitmap.getBitmap()).drawColor(withColor)
    }

    companion object {
        private var mInstance: Creator = Creator()

        @Synchronized
        internal fun getInstance() : Creator {
            return mInstance
        }
    }
}