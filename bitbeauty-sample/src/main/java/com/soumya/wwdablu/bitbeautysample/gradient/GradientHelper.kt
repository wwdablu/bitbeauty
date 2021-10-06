package com.soumya.wwdablu.bitbeautysample.gradient

import android.content.Context
import android.graphics.Color
import com.soumya.wwdablu.bitbeauty.BitBeauty
import com.soumya.wwdablu.bitbeauty.BitBeautyBitmap
import com.soumya.wwdablu.bitbeauty.modules.gradient.Gradient

class GradientHelper {

    fun linearGradient(context: Context): BitBeautyBitmap? {

        val bmp = BitBeauty.Creator.createBitmap(context, 600, 600, Color.TRANSPARENT) ?: return null
        val ca = IntArray(3)
        ca[0] = Color.RED
        ca[1] = Color.GREEN
        ca[2] = Color.BLUE
        BitBeauty.LinearGradient.drawRect(bmp, 0F, 0F, 600F, 600F, ca, null, Gradient.Mode.CLAMP)
        return bmp
    }

    fun radial(context: Context): BitBeautyBitmap? {

        val bmp = BitBeauty.Creator.createBitmap(context, 600, 600, Color.TRANSPARENT) ?: return null
        val ca = IntArray(5)
        ca[0] = Color.BLACK
        ca[1] = Color.RED
        ca[2] = Color.BLUE
        ca[3] = Color.GREEN
        ca[4] = Color.YELLOW
        BitBeauty.RadialGradient.drawCircle(bmp, 300F, 300F, 300F, false, ca, null, Gradient.Mode.CLAMP)

        return bmp
    }
}