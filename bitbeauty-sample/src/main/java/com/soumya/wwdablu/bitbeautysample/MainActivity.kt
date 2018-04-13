package com.soumya.wwdablu.bitbeautysample

import android.graphics.Color
import android.graphics.Path
import android.graphics.PointF
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import com.soumya.wwdablu.bitbeauty.BitBeauty
import com.soumya.wwdablu.bitbeauty.BitBeautyBitmap
import com.soumya.wwdablu.bitbeauty.modules.gradient.Gradient
import com.soumya.wwdablu.bitbeauty.modules.writer.BitmapWriter
import com.soumya.wwdablu.bitbeautysample.editors.MaskImage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //----- Shapes -----
        //LinkedCubes().drawJoinedCubes(this, findViewById(R.id.iv_image), false)
        //Polygons().draw(this, findViewById(R.id.iv_image))

        //----- Editor -----
        //MaskImage().maskImage(this, findViewById(R.id.iv_image))
        MaskImage().animateReveal(this, findViewById(R.id.iv_image))
    }



    fun simpleColorBitmap() {

        val bmp = BitBeauty.Creator.createBitmap(this, 200, 200, Color.RED)
        findViewById<ImageView>(R.id.iv_image).setImageBitmap((bmp!!.getBitmap()))
    }

    fun imageBitmapFromUrl() {

        val url = "http://www.cameraegg.org/wp-content/uploads/2015/06/canon-powershot-g3-x-sample-images-1-620x413.jpg"

        BitBeauty.Creator.createBitmapFromUrl(this, url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableObserver<BitBeautyBitmap>() {
                override fun onComplete() {
                    //
                }

                override fun onNext(t: BitBeautyBitmap) {

                    val cropped = BitBeauty.Editor.crop(applicationContext, t, PointF(380F, 150F), 150F)
                    val rotate = BitBeauty.Editor.rotate(applicationContext, cropped!!, -45F)
                    //BitBeauty.Effects.sepia(cropped!!)
                    //BitBeauty.Effects.grayscale(rotate!!)
                    //BitBeauty.Effects.polaroid(rotate!!)
                    //BitBeauty.Effects.blankAndWhite(rotate!!)
                    //BitBeauty.Shapes.drawCircle(rotate!!, Color.parseColor("#88000000"), 150F, Point(212, 212))
                    //BitBeauty.Shapes.drawOval(rotate!!, Color.BLACK, RectF(50F, 100F, 150F, 120F))
                    //BitBeauty.Shapes.drawRect(rotate!!, Color.BLACK, RectF(50F, 100F, 150F, 120F))

                    val path = Path()
                    path.moveTo(100F, 100F)
                    path.lineTo(100F, 100F)
                    path.lineTo(100F, 200F)
                    path.lineTo(200F, 300F)
                    path.lineTo(200F, 100F)
                    path.lineTo(100F, 100F)
                    path.close()
                    //BitBeauty.Shapes.freeform(rotate!!, Color.parseColor("#88FFFFFF"), path)

                    findViewById<ImageView>(R.id.iv_image).setImageBitmap((rotate!!.getBitmap()))
                }

                override fun onError(e: Throwable) {
                    //
                }
            })
    }

    fun linearAndRadialGradient() : BitBeautyBitmap {

        val bmp = BitBeauty.Creator.createBitmap(this, 200, 200, Color.WHITE)

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
