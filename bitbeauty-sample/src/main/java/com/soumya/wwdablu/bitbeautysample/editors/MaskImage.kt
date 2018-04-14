package com.soumya.wwdablu.bitbeautysample.editors

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.PorterDuff
import android.widget.ImageView
import com.soumya.wwdablu.bitbeauty.BitBeauty
import com.soumya.wwdablu.bitbeauty.BitBeautyBitmap
import com.soumya.wwdablu.bitbeautysample.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MaskImage {

    fun maskImage(context: Context, imageView: ImageView) {

        BitBeauty.Creator.createBitmapFromDrawable(context, R.drawable.sunflower)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<BitBeautyBitmap>() {
                override fun onComplete() {
                    //
                }

                override fun onNext(t: BitBeautyBitmap) {
                    val hexagonBmp: BitBeautyBitmap? = BitBeauty.Creator.createBitmap(context,
                            t.getBitmap()?.width ?: 0, t.getBitmap()?.height ?: 0, Color.TRANSPARENT)

                    hexagonBmp ?: return
                    hexagonBmp.getBitmap() ?: return

                    BitBeauty.Shapes.drawPolygon(hexagonBmp, Color.BLACK, ((hexagonBmp.getBitmap()?.width?.toFloat() ?: 0F) / 2F) - 50F,
                            ((hexagonBmp.getBitmap()?.height?.toFloat() ?: 0F) / 2F) - 50F, 150F, 6)

                    BitBeauty.Editor.mask(t, hexagonBmp, Point(0,0))
                    imageView.setImageBitmap((hexagonBmp.getBitmap()))
                }

                override fun onError(e: Throwable) {
                    //
                }
            })
    }

    fun animateReveal(context: Context, imageView: ImageView) {

        BitBeauty.Creator.createBitmapFromDrawable(context, R.drawable.sunflower)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<BitBeautyBitmap>() {
                    override fun onComplete() {
                        //
                    }

                    override fun onNext(t: BitBeautyBitmap) {
                        reveal(context, imageView, t)
                    }

                    override fun onError(e: Throwable) {
                        //
                    }
                })
    }

    private fun reveal(context: Context, imageView: ImageView, bitBeautyBitmap: BitBeautyBitmap) {

        val maskBitmap = BitBeauty.Creator.createBitmap(context, bitBeautyBitmap.getBitmap()?.width ?: 0,
                bitBeautyBitmap.getBitmap()?.height ?: 0, Color.TRANSPARENT) ?: return

        val canvasBitmap = BitBeauty.Editor.clone(context, maskBitmap) ?: return

        Observable.interval(8, TimeUnit.MILLISECONDS)
            .takeWhile({ t:Long -> t <= 1200})
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Long>() {
                override fun onComplete() {
                    //
                }

                override fun onNext(t: Long) {

                    if(t <= 600) {
                        BitBeauty.Editor.erase(maskBitmap, Color.TRANSPARENT)

                        BitBeauty.Shapes.drawCircle(maskBitmap, Color.BLACK, t.toFloat(), Point(
                                300, 300
                        ))

                        BitBeauty.Editor.mask(bitBeautyBitmap, maskBitmap, Point(0,0))
                        BitBeauty.Editor.copy(maskBitmap, canvasBitmap)

                        imageView.setImageBitmap((canvasBitmap.getBitmap()))
                    } else {
                        BitBeauty.Editor.erase(maskBitmap, Color.TRANSPARENT)
                        BitBeauty.Shapes.drawCircle(maskBitmap, Color.BLACK, (1200 - t).toFloat(), Point(
                                300, 300
                        ))

                        BitBeauty.Editor.mask(bitBeautyBitmap, maskBitmap, Point(0,0), PorterDuff.Mode.SRC_IN)
                        BitBeauty.Editor.copy(maskBitmap, canvasBitmap)
                        imageView.setImageBitmap((canvasBitmap.getBitmap()))
                    }
                }

                override fun onError(e: Throwable) {
                    //
                }
            })
    }
}