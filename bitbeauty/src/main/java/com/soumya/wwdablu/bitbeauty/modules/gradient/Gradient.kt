package com.soumya.wwdablu.bitbeauty.modules.gradient

import android.graphics.Shader

/**
 * Created by soumya on 3/25/18.
 */
class Gradient {

    companion object {
        internal fun convertShaderMode(mode:Gradient.Mode) : Shader.TileMode {
            return when (mode) {
                Gradient.Mode.MIRROR -> Shader.TileMode.MIRROR
                Gradient.Mode.REPEAT -> Shader.TileMode.REPEAT
                Gradient.Mode.CLAMP -> Shader.TileMode.CLAMP
            }
        }
    }

    enum class Mode {
        CLAMP,
        MIRROR,
        REPEAT
    }
}