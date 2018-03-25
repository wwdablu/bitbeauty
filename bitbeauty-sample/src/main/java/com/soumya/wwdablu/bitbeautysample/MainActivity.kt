package com.soumya.wwdablu.bitbeautysample

import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import com.soumya.wwdablu.bitbeauty.BitBeauty

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var bmp = BitBeauty.getInstance().createBitmapRGB(this, 200, 200, Color.RED)
        findViewById<ConstraintLayout>(R.id.cl_container).background = BitmapDrawable(bmp!!.getBitmap())
    }
}
