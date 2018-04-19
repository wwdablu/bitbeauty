package com.soumya.wwdablu.bitbeautysample.shapes

import android.content.Context
import android.graphics.Color
import android.widget.ImageView
import com.soumya.wwdablu.bitbeauty.BitBeauty
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import java.util.concurrent.TimeUnit


class Polygons {

    fun draw(context: Context, imageView: ImageView) {

        val bmp = BitBeauty.Creator.createBitmap(context, 800, 800, Color.BLACK) ?: return

        Observable.interval(5000L,1000L, TimeUnit.MILLISECONDS)
            .takeWhile { t: Long -> t <= 10 }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<Long>() {
                override fun onComplete() {
                    //
                }

                override fun onNext(t: Long) {

                    BitBeauty.Editor.erase(bmp, Color.BLACK)
                    BitBeauty.Shapes.drawPolygon(bmp, Color.WHITE, 380F, 350F, 250F, t.toInt())
                    imageView.setImageBitmap(bmp.getBitmap())
                }

                override fun onError(e: Throwable) {
                    //
                }
            })
    }
}