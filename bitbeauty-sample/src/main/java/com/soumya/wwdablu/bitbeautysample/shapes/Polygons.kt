package com.soumya.wwdablu.bitbeautysample.shapes

import android.content.Context
import android.graphics.Color
import android.widget.ImageView
import com.soumya.wwdablu.bitbeauty.BitBeauty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class Polygons {

    fun draw(context: Context, imageView: ImageView) {

        val bmp = BitBeauty.Creator.createBitmap(context, 800, 800, Color.BLACK) ?: return

        CoroutineScope(Dispatchers.Main).launch {
            var polygonSide = 2
            while (polygonSide++ != 15) {
                delay(500L)
                BitBeauty.Editor.erase(bmp, Color.BLACK)
                BitBeauty.Shapes.drawPolygon(bmp, Color.WHITE, 380F, 350F, 250F, polygonSide)
                imageView.setImageBitmap(bmp.getBitmap())
            }
        }
    }
}