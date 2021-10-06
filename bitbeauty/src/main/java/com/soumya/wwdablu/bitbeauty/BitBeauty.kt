package com.soumya.wwdablu.bitbeauty

import com.soumya.wwdablu.bitbeauty.modules.creator.Creator
import com.soumya.wwdablu.bitbeauty.modules.editor.Editor
import com.soumya.wwdablu.bitbeauty.modules.effects.Effects
import com.soumya.wwdablu.bitbeauty.modules.gradient.LinearGradient
import com.soumya.wwdablu.bitbeauty.modules.gradient.RadialGradient
import com.soumya.wwdablu.bitbeauty.modules.shapes.Shapes
import com.soumya.wwdablu.bitbeauty.modules.text.Text
import com.soumya.wwdablu.bitbeauty.modules.writer.BitmapWriter

/**
 * Created by soumya on 3/23/18.
 */
object BitBeauty {

    /**
     * Returns the Creator using which bitmaps can be created
     */
    val Creator: Creator by lazy { Creator() }

    /**
     * Returns a LinearGradient instance which can be used by the application
     */
    val LinearGradient: LinearGradient by lazy { LinearGradient() }

    /**
     * Returns a Radian Gradient instance which can be used by the application
     */
    val RadialGradient: RadialGradient by lazy { RadialGradient() }

    /**
     * Returns an Effects instance which can be used to apply effects into BitBeautyBitmap
     */
    val Effects: Effects by lazy { Effects() }

    /**
     * Returns an Editor instance which can be used to edit existing BitBeautyBitmaps
     */
    val Editor: Editor by lazy { Editor() }

    /**
     * Returns a Shapes instance which can be used to draw shapes on a bitmap
     */
    val Shapes: Shapes by lazy { Shapes() }

    /**
     * Returns a Text instance which can be used to draw shapes on a bitmap
     */
    val Text: Text by lazy { Text() }

    /**
     * Returns a write object which can be used write the bitmap into a file
     */
    val BitmapWriter: BitmapWriter by lazy { BitmapWriter() }
}