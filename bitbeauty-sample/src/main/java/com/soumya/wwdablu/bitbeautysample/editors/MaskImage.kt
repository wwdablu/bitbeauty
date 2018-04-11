package com.soumya.wwdablu.bitbeautysample.editors

import android.content.Context
import android.graphics.Color
import android.widget.ImageView
import com.soumya.wwdablu.bitbeauty.BitBeauty
import com.soumya.wwdablu.bitbeauty.BitBeautyBitmap
import com.soumya.wwdablu.bitbeautysample.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

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

                        BitBeauty.Editor.mask(t, hexagonBmp)
                        imageView.setImageBitmap((hexagonBmp.getBitmap()))
                    }

                    override fun onError(e: Throwable) {
                        //
                    }
                })
    }
}