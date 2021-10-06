package com.soumya.wwdablu.bitbeauty.modules.gradient

import android.graphics.Shader

/**
 * Created by soumya on 3/25/18.
 */
abstract class Gradient internal constructor() {

    companion object {
        internal fun convertShaderMode(mode:Gradient.Mode) : Shader.TileMode {
            return when (mode) {
                Mode.MIRROR -> Shader.TileMode.MIRROR
                Mode.REPEAT -> Shader.TileMode.REPEAT
                Mode.CLAMP -> Shader.TileMode.CLAMP
            }
        }
    }

    enum class Mode {
        CLAMP,
        MIRROR,
        REPEAT
    }
}