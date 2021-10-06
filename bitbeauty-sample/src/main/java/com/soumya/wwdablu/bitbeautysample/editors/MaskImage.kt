package com.soumya.wwdablu.bitbeautysample.editors

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.PorterDuff
import android.widget.ImageView
import com.soumya.wwdablu.bitbeauty.BitBeauty
import com.soumya.wwdablu.bitbeauty.BitBeautyBitmap
import com.soumya.wwdablu.bitbeautysample.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MaskImage {

    fun maskImage(context: Context, imageView: ImageView) {

        CoroutineScope(Dispatchers.Main).launch {
            val beautyBitmap = BitBeauty.Creator.createBitmapFromDrawable(context, R.drawable.sunflower)

            val hexagonBmp: BitBeautyBitmap? = BitBeauty.Creator.createBitmap(context,
                beautyBitmap.getBitmap().width, beautyBitmap.getBitmap().height, Color.TRANSPARENT)

            hexagonBmp ?: return@launch

            BitBeauty.Shapes.drawPolygon(hexagonBmp, Color.BLACK, ((hexagonBmp.getBitmap().width.toFloat()) / 2F) - 50F,
                ((hexagonBmp.getBitmap().height.toFloat()) / 2F) - 50F, 150F, 6)

            BitBeauty.Editor.mask(beautyBitmap, hexagonBmp, Point(0,0))
            imageView.setImageBitmap((hexagonBmp.getBitmap()))
        }
    }

    fun animateReveal(context: Context, imageView: ImageView) {

        CoroutineScope(Dispatchers.Main).launch {
            val beautyBitmap = BitBeauty.Creator.createBitmapFromDrawable(context, R.drawable.sunflower)
            reveal(context, imageView, beautyBitmap)
        }
    }

    private suspend fun reveal(context: Context, imageView: ImageView, bitBeautyBitmap: BitBeautyBitmap) {

        val maskBitmap = BitBeauty.Creator.createBitmap(context, bitBeautyBitmap.getBitmap().width,
                bitBeautyBitmap.getBitmap().height, Color.TRANSPARENT) ?: return

        val canvasBitmap = BitBeauty.Editor.clone(context, maskBitmap) ?: return

        var revealStep = 0
        while (revealStep++ != 1200) {
            delay(8L)
            if(revealStep <= 600) {
                BitBeauty.Editor.erase(maskBitmap, Color.TRANSPARENT)

                BitBeauty.Shapes.drawCircle(maskBitmap, Color.BLACK, revealStep.toFloat(), Point(
                    300, 300
                ))

                BitBeauty.Editor.mask(bitBeautyBitmap, maskBitmap, Point(0,0))
                BitBeauty.Editor.copy(maskBitmap, canvasBitmap)

                imageView.setImageBitmap((canvasBitmap.getBitmap()))
            } else {
                BitBeauty.Editor.erase(maskBitmap, Color.TRANSPARENT)
                BitBeauty.Shapes.drawCircle(maskBitmap, Color.BLACK, (1200 - revealStep).toFloat(), Point(
                    300, 300
                ))

                BitBeauty.Editor.mask(bitBeautyBitmap, maskBitmap, Point(0,0), PorterDuff.Mode.SRC_IN)
                BitBeauty.Editor.copy(maskBitmap, canvasBitmap)
                imageView.setImageBitmap((canvasBitmap.getBitmap()))
            }
        }
    }
}