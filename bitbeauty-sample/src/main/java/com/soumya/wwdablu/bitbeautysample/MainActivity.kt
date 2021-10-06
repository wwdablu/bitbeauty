package com.soumya.wwdablu.bitbeautysample

import android.graphics.Color
import android.graphics.Point
import android.graphics.PointF
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.soumya.wwdablu.bitbeauty.BitBeauty
import com.soumya.wwdablu.bitbeauty.BitBeautyBitmap
import com.soumya.wwdablu.bitbeauty.modules.gradient.Gradient
import com.soumya.wwdablu.bitbeautysample.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        //----- Shapes -----
        //LinkedCubes().drawJoinedCubes(this, mBinding.ivImage)
        //Polygons().draw(this, mBinding.ivImage)

        //----- Editor -----
        //MaskImage().maskImage(this, mBinding.ivImage)
        //MaskImage().animateReveal(this, mBinding.ivImage)

        //mBinding.ivImage.setImageBitmap(GradientHelper().linearGradient(this)?.getBitmap())

        //mBinding.ivImage.setImageBitmap(GradientHelper().radial(applicationContext)?.getBitmap())

        imageBitmapFromUrl()
    }

    fun simpleColorBitmap() {

        val bmp = BitBeauty.Creator.createBitmap(this, 200, 200, Color.RED)
        mBinding.ivImage.setImageBitmap((bmp!!.getBitmap()))
    }

    private fun imageBitmapFromUrl() {

        val url = "https://avatars0.githubusercontent.com/u/28639189?s=400&u=bd9a720624781e17b9caaa1489345274c07566ac&v=4"

        CoroutineScope(Dispatchers.Main).launch {
            BitBeauty.Creator.createBitmapFromUrl(applicationContext, url).also { t ->
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
        }
    }
}
