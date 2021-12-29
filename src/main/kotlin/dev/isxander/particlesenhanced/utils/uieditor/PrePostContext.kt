package dev.isxander.particlesenhanced.utils.uieditor

import net.minecraft.client.gui.GuiScreen

class PrePostContext<T>(defaults: (Type) -> T) {
    var pre: T = defaults(Type.PRE)
        private set
    var post: T = defaults(Type.POST)
        private set

    fun pre(block: T) {
        pre = block
    }
    fun post(block: T) {
        post = block
    }

    enum class Type {
        PRE,
        POST
    }
}
