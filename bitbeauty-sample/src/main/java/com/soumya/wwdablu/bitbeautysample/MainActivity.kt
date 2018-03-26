package com.soumya.wwdablu.bitbeautysample

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.soumya.wwdablu.bitbeauty.BitBeauty
import com.soumya.wwdablu.bitbeauty.modules.gradient.Gradient

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bmp = BitBeauty.getInstance().createBitmapRGB(this, 200, 200, Color.WHITE)

        val ca = IntArray(2)
        ca[0] = Color.CYAN
        ca[1] = Color.YELLOW
        //ca[2] = Color.MAGENTA
        //BitBeauty.getInstance().linearGradientRect(bmp!!, 0F, 0F, 200F, 200F, ca, null, Gradient.Mode.CLAMP)
        BitBeauty.getInstance().linearGradientRect(bmp!!, 0F, 0F, 200F, 200F, 0F, 100F, 200F, 100F, ca, null, Gradient.Mode.CLAMP)

        val d = IntArray(3)
        d[0] = Color.RED
        d[1] = Color.GREEN
        d[2] = Color.BLUE

        val f = FloatArray(d.size)
        f[0] = 0.0f
        f[1] = 0.33f
        f[2] = 0.66f

        //BitBeauty.getInstance().radialGradientCircle(bmp, 100F, 100F, 50F, false, d, null, Gradient.Mode.MIRROR)
        BitBeauty.getInstance().radialGradientOval(bmp, 0F, 80F, 200F, 120F, true, d, null, Gradient.Mode.CLAMP)
        BitBeauty.getInstance().radialGradientCircle(bmp, 0F, 0F, 25F, 35F, 50F, false, d, null, Gradient.Mode.MIRROR)
        BitBeauty.getInstance().radialGradientCircle(bmp, 200F, 200F, 200F, 200F, 50F, false, d, null, Gradient.Mode.CLAMP)

        findViewById<ImageView>(R.id.iv_image).setImageBitmap((bmp.getBitmap()))
    }
}
