package com.soumya.wwdablu.bitbeauty

import com.soumya.wwdablu.bitbeauty.modules.Effects
import com.soumya.wwdablu.bitbeauty.modules.creator.Creator
import com.soumya.wwdablu.bitbeauty.modules.gradient.LinearGradient
import com.soumya.wwdablu.bitbeauty.modules.gradient.RadialGradient
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

        val Effects: Effects
        get() = com.soumya.wwdablu.bitbeauty.modules.Effects.getInstance()

        /**
         * Returns a write object which can be used write the bitmap into a file
         */
        val BitmapWriter: BitmapWriter
        get() = com.soumya.wwdablu.bitbeauty.modules.writer.BitmapWriter.getInstance()
    }
}