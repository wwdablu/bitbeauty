package com.soumya.wwdablu.bitbeautysample

import android.graphics.*
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import com.soumya.wwdablu.bitbeauty.BitBeauty
import com.soumya.wwdablu.bitbeauty.BitBeautyBitmap
import com.soumya.wwdablu.bitbeauty.modules.gradient.Gradient
import com.soumya.wwdablu.bitbeauty.modules.writer.BitmapWriter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //simpleColorBitmap()
        //val bmp = linearAndRadialGradient()
        imageBitmap()
        //writeBitmap(bmp)
    }

    fun simpleColorBitmap() {

        val bmp = BitBeauty.Creator.createBitmapRGB(this, 200, 200, Color.RED)
        findViewById<ImageView>(R.id.iv_image).setImageBitmap((bmp!!.getBitmap()))
    }

    fun imageBitmap() {

        BitBeauty.Creator.createBitmap(this, R.drawable.sunflower)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<BitBeautyBitmap>() {
                override fun onComplete() {
                    //
                }

                override fun onNext(t: BitBeautyBitmap) {
                    //BitBeauty.Effects.toSepia(t)
                    //BitBeauty.Effects.toGrayScale(t)
                    BitBeauty.Effects.invert(t)
                    findViewById<ImageView>(R.id.iv_image).setImageBitmap((t!!.getBitmap()))
                }

                override fun onError(e: Throwable) {
                    //
                }
            })
    }

    fun linearAndRadialGradient() : BitBeautyBitmap {

        val bmp = BitBeauty.Creator.createBitmapRGB(this, 200, 200, Color.WHITE)

        val ca = IntArray(3)
        ca[0] = Color.CYAN
        ca[1] = Color.YELLOW
        ca[2] = Color.MAGENTA

        BitBeauty.LinearGradient.drawRect(bmp!!, 0F, 0F, 200F, 200F, 0F, 100F, 200F, 100F, ca, null, Gradient.Mode.CLAMP)

        val d = IntArray(3)
        d[0] = Color.RED
        d[1] = Color.GREEN
        d[2] = Color.BLUE

        val f = FloatArray(d.size)
        f[0] = 0.0f
        f[1] = 0.33f
        f[2] = 0.66f

        BitBeauty.RadialGradient.drawCircle(bmp, 100F, 100F, 50F, false, d, null, Gradient.Mode.MIRROR)

        findViewById<ImageView>(R.id.iv_image).setImageBitmap((bmp.getBitmap()))

        return bmp
    }

    fun writeBitmap(bmp:BitBeautyBitmap) {

        val file = File(this.filesDir, "gradient.jpg")
        Log.e("TAG", file.path)

        BitBeauty.BitmapWriter.write(bmp, file, BitmapWriter.Format.JPEG, 100)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<File>() {
                override fun onComplete() {
                    //
                }

                override fun onNext(t: File) {
                    //
                }

                override fun onError(e: Throwable) {
                    //
                }
            })
    }
}
