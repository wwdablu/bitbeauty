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
import com.soumya.wwdablu.bitbeautysample.editors.MaskImage
import com.soumya.wwdablu.bitbeautysample.shapes.LinkedCubes
import com.soumya.wwdablu.bitbeautysample.shapes.Polygons
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
        //MaskImage().animateReveal(this, findViewById(R.id.iv_image))

//        findViewById<ImageView>(R.id.iv_image).setImageBitmap(
//                com.soumya.wwdablu.bitbeautysample.gradient.Gradient().linearGradient(applicationContext)?.getBitmap())

//        findViewById<ImageView>(R.id.iv_image).setImageBitmap(
//                com.soumya.wwdablu.bitbeautysample.gradient.Gradient().radial(applicationContext)?.getBitmap())

        imageBitmapFromUrl()
    }



    fun simpleColorBitmap() {

        val bmp = BitBeauty.Creator.createBitmap(this, 200, 200, Color.RED)
        findViewById<ImageView>(R.id.iv_image).setImageBitmap((bmp!!.getBitmap()))
    }

    fun imageBitmapFromUrl() {

        val url = "https://avatars0.githubusercontent.com/u/28639189?s=400&u=bd9a720624781e17b9caaa1489345274c07566ac&v=4"

        BitBeauty.Creator.createBitmapFromUrl(this, url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableObserver<BitBeautyBitmap>() {
                override fun onComplete() {
                    //
                }

                override fun onNext(t: BitBeautyBitmap) {

                    //Resize the original bitmap for better calculation
                    t.resize(applicationContext,600, 600, false)

                    //Draw the small holders on the corners as if they are holding the image
                    BitBeauty.Shapes.drawCircle(t, Color.WHITE, 30F, Point(0,0))
                    BitBeauty.Shapes.drawCircle(t, Color.WHITE, 30F, Point(0,600))
                    BitBeauty.Shapes.drawCircle(t, Color.WHITE, 30F, Point(600,0))
                    BitBeauty.Shapes.drawCircle(t, Color.WHITE, 30F, Point(600,600))

                    //Create the bigger frame (also to make the pin half outside the image)
                    val frame = BitBeauty.Creator.createBitmap(applicationContext, 700, 900, Color.TRANSPARENT)

                    //Create the frame to contain the image
                    val frameContainer = BitBeauty.Creator.createBitmap(applicationContext, 700, 800, Color.WHITE)
                    val ca = IntArray(5)
                    ca[0] = Color.WHITE
                    ca[1] = Color.LTGRAY
                    ca[2] = Color.GRAY
                    ca[3] = Color.LTGRAY
                    ca[4] = Color.WHITE
                    BitBeauty.LinearGradient.drawRect(frameContainer!!, 0F, 0F, 700F, 900F, 0F, 100F, 500F, 600F, ca, null, Gradient.Mode.CLAMP)

                    BitBeauty.Editor.combine(frameContainer, frame!!, Point(0, 100))
                    BitBeauty.Editor.combine(t, frame, Point(100, 250))

                    //Create the red pin to hold the image
                    BitBeauty.Shapes.drawCircle(frame, Color.RED, 50F, Point(350, 50))

                    //Write the text at the bottom
                    BitBeauty.Text.write(frame, "Soumya Kanti Kar", 75F, Color.BLACK, PointF(50F, 810F))

                    //Rotate the image 10 degree to give it a tilt feel
                    val rotate = frame.rotate(applicationContext, frame, -10F)

                    //Show in the UI
                    findViewById<ImageView>(R.id.iv_image).setImageBitmap((rotate!!.getBitmap()))
                }

                override fun onError(e: Throwable) {
                    val s = ""
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
