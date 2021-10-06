package com.soumya.wwdablu.bitbeautysample.shapes

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Point
import android.graphics.PointF
import android.widget.ImageView
import com.soumya.wwdablu.bitbeauty.BitBeauty
import com.soumya.wwdablu.bitbeauty.BitBeautyBitmap

class LinkedCubes {

    fun drawJoinedCubes(context: Context, imageView: ImageView) : BitBeautyBitmap? {

        val bmp = BitBeauty.Creator.createBitmap(context, 400, 400, Color.TRANSPARENT, Bitmap.Config.ARGB_8888) ?: return null

        //Back ------------------------
        BitBeauty.Shapes.apply {
            //Upper two dots
            drawCircle(bmp, Color.BLACK, 15F, Point(120, 30))
            drawCircle(bmp, Color.BLACK, 15F, Point(280, 30))

            //Lower two dots
            drawCircle(bmp, Color.BLACK, 15F, Point(120, 290))
            drawCircle(bmp, Color.BLACK, 15F, Point(280, 290))

            //Connect the dots horizontally
            drawLine(bmp, Color.BLACK, 10F, PointF(120F, 30F), PointF(280F, 30F))
            drawLine(bmp, Color.BLACK, 10F, PointF(120F, 290F), PointF(280F, 290F))

            //Connect the dots vertically
            drawLine(bmp, Color.BLACK, 10F, PointF(120F, 30F), PointF(120F, 290F))
            drawLine(bmp, Color.BLACK, 10F, PointF(280F, 30F), PointF(280F, 290F))
        }

        //Front -----------------------
        BitBeauty.Shapes.apply {
            //Upper two dots
            drawCircle(bmp, Color.BLACK, 15F, Point(200, 110))
            drawCircle(bmp, Color.BLACK, 15F, Point(360, 110))

            //Lower two dots
            drawCircle(bmp, Color.BLACK, 15F, Point(200, 370))
            drawCircle(bmp, Color.BLACK, 15F, Point(360, 370))

            //Connect the dots horizontally
            drawLine(bmp, Color.BLACK, 10F, PointF(200F, 110F), PointF(360F, 110F))
            drawLine(bmp, Color.BLACK, 10F, PointF(200F, 370F), PointF(360F, 370F))

            //Connect the dots vertically
            drawLine(bmp, Color.BLACK, 10F, PointF(200F, 110F), PointF(200F, 370F))
            drawLine(bmp, Color.BLACK, 10F, PointF(360F, 110F), PointF(360F, 370F))

            //Connect the level dots
            drawLine(bmp, Color.BLACK, 10F, PointF(120F, 30F), PointF(200F, 110F))
            drawLine(bmp, Color.BLACK, 10F, PointF(280F, 30F), PointF(360F, 110F))
            drawLine(bmp, Color.BLACK, 10F, PointF(120F, 290F), PointF(200F, 370F))
            drawLine(bmp, Color.BLACK, 10F, PointF(280F, 290F), PointF(360F, 370F))
        }

        imageView.setImageBitmap((bmp.getBitmap()))

        return bmp
    }
}