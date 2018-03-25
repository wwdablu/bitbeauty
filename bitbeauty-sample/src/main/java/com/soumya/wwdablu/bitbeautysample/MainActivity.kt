package com.soumya.wwdablu.bitbeautysample

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.soumya.wwdablu.bitbeauty.BitBeauty

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bmp = BitBeauty.getInstance().createBitmapRGB(this, 200, 200, Color.GRAY)
        //BitBeauty.getInstance().circularRadialGradient(bmp!!, 100F, 100F, 50F, true, Color.BLACK, Color.BLUE)

        val d = IntArray(3)
        d[0] = Color.BLACK
        d[1] = Color.RED
        d[2] = Color.BLUE

        val f = FloatArray(d.size)
        f[0] = 0.0f
        f[1] = 0.33f
        f[2] = 0.66f

        BitBeauty.getInstance().circularRadialGradient(bmp!!, 100F, 100F, 50F, false, d, null)
        findViewById<ImageView>(R.id.iv_image).setImageBitmap((bmp!!.getBitmap()))
    }
}
