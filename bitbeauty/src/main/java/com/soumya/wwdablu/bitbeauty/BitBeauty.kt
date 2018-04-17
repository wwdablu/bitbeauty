package com.soumya.wwdablu.bitbeauty

import com.soumya.wwdablu.bitbeauty.modules.effects.Effects
import com.soumya.wwdablu.bitbeauty.modules.creator.Creator
import com.soumya.wwdablu.bitbeauty.modules.editor.Editor
import com.soumya.wwdablu.bitbeauty.modules.gradient.LinearGradient
import com.soumya.wwdablu.bitbeauty.modules.gradient.RadialGradient
import com.soumya.wwdablu.bitbeauty.modules.shapes.Shapes
import com.soumya.wwdablu.bitbeauty.modules.text.Text
import com.soumya.wwdablu.bitbeauty.modules.writer.BitmapWriter

/**
 * Created by soumya on 3/23/18.
 */
class BitBeauty private constructor() {

    companion object {

        /**
         * Returns the Creator using which bitmaps can be created
         */
        val Creator: Creator
        get() = com.soumya.wwdablu.bitbeauty.modules.creator.Creator.getInstance()

        /**
         * Returns a LinearGradient instance which can be used by the application
         */
        val LinearGradient: LinearGradient
        get() = com.soumya.wwdablu.bitbeauty.modules.gradient.LinearGradient.getInstance()

        /**
         * Returns a Radian Gradient instance which can be used by the application
         */
        val RadialGradient: RadialGradient
        get() = com.soumya.wwdablu.bitbeauty.modules.gradient.RadialGradient.getInstance()

        /**
         * Returns an Effects instance which can be used to apply effects into BitBeautyBitmap
         */
        val Effects: Effects
        get() = com.soumya.wwdablu.bitbeauty.modules.effects.Effects.getInstance()

        /**
         * Returns an Editor instance which can be used to edit existing BitBeautyBitmaps
         */
        val Editor: Editor
        get() = com.soumya.wwdablu.bitbeauty.modules.editor.Editor.getInstance()

        /**
         * Returns a Shapes instance which can be used to draw shapes on a bitmap
         */
        val Shapes: Shapes
        get() = com.soumya.wwdablu.bitbeauty.modules.shapes.Shapes.getInstance()

        /**
         * Returns a Text instance which can be used to draw shapes on a bitmap
         */
        val Text: Text
        get() = com.soumya.wwdablu.bitbeauty.modules.text.Text.getInstance()

        /**
         * Returns a write object which can be used write the bitmap into a file
         */
        val BitmapWriter: BitmapWriter
        get() = com.soumya.wwdablu.bitbeauty.modules.writer.BitmapWriter.getInstance()
    }
}