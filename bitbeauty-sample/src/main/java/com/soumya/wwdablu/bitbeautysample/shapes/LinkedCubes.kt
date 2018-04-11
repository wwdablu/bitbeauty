package com.soumya.wwdablu.bitbeautysample.shapes

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Point
import android.graphics.PointF
import android.widget.ImageView
import com.soumya.wwdablu.bitbeauty.BitBeauty
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import java.util.concurrent.TimeUnit

class LinkedCubes {

    fun drawJoinedCubes(context: Context, imageView: ImageView, animated: Boolean = false) {

        val bmp = BitBeauty.Creator.createBitmap(context, 400, 400, Color.TRANSPARENT, Bitmap.Config.ARGB_8888)
                ?: return

        //Back ------------------------
        //Upper two dots
        BitBeauty.Shapes.drawCircle(bmp, Color.BLACK, 15F, Point(120, 30))
        BitBeauty.Shapes.drawCircle(bmp, Color.BLACK, 15F, Point(280, 30))

        //Lower two dots
        BitBeauty.Shapes.drawCircle(bmp, Color.BLACK, 15F, Point(120, 290))
        BitBeauty.Shapes.drawCircle(bmp, Color.BLACK, 15F, Point(280, 290))

        //Connect the dots horizontally
        BitBeauty.Shapes.drawLine(bmp, Color.BLACK, 10F, PointF(120F, 30F), PointF(280F, 30F))
        BitBeauty.Shapes.drawLine(bmp, Color.BLACK, 10F, PointF(120F, 290F), PointF(280F, 290F))

        //Connect the dots vertically
        BitBeauty.Shapes.drawLine(bmp, Color.BLACK, 10F, PointF(120F, 30F), PointF(120F, 290F))
        BitBeauty.Shapes.drawLine(bmp, Color.BLACK, 10F, PointF(280F, 30F), PointF(280F, 290F))

        //Front -----------------------
        //Upper two dots
        BitBeauty.Shapes.drawCircle(bmp, Color.BLACK, 15F, Point(200, 110))
        BitBeauty.Shapes.drawCircle(bmp, Color.BLACK, 15F, Point(360, 110))

        //Lower two dots
        BitBeauty.Shapes.drawCircle(bmp, Color.BLACK, 15F, Point(200, 370))
        BitBeauty.Shapes.drawCircle(bmp, Color.BLACK, 15F, Point(360, 370))

        //Connect the dots horizontally
        BitBeauty.Shapes.drawLine(bmp, Color.BLACK, 10F, PointF(200F, 110F), PointF(360F, 110F))
        BitBeauty.Shapes.drawLine(bmp, Color.BLACK, 10F, PointF(200F, 370F), PointF(360F, 370F))

        //Connect the dots vertically
        BitBeauty.Shapes.drawLine(bmp, Color.BLACK, 10F, PointF(200F, 110F), PointF(200F, 370F))
        BitBeauty.Shapes.drawLine(bmp, Color.BLACK, 10F, PointF(360F, 110F), PointF(360F, 370F))

        //Connect the level dots
        BitBeauty.Shapes.drawLine(bmp, Color.BLACK, 10F, PointF(120F, 30F), PointF(200F, 110F))
        BitBeauty.Shapes.drawLine(bmp, Color.BLACK, 10F, PointF(280F, 30F), PointF(360F, 110F))
        BitBeauty.Shapes.drawLine(bmp, Color.BLACK, 10F, PointF(120F, 290F), PointF(200F, 370F))
        BitBeauty.Shapes.drawLine(bmp, Color.BLACK, 10F, PointF(280F, 290F), PointF(360F, 370F))


        if(animated) {
            val observable = Observable.interval(33L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread())
            observable.subscribe(object : DisposableObserver<Long>() {
                override fun onComplete() {
                    //
                }

                override fun onNext(t: Long) {
                    val rotateBitmap = BitBeauty.Editor.rotate(context, bmp, (t).toFloat())
                            ?: bmp
                    imageView.setImageBitmap((rotateBitmap.getBitmap()))
                }

                override fun onError(e: Throwable) {
                    //
                }
            })
        } else {
            imageView.setImageBitmap((bmp.getBitmap()))
        }
    }
}