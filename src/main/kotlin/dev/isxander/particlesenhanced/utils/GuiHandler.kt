package dev.isxander.particlesenhanced.utils

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.minecraft.client.gui.screen.Screen

object GuiHandler {

    private var screen: Screen? = null

    init {
        ClientTickEvents.END_CLIENT_TICK.register {
            if (screen != null) {
                it.setScreen(screen)
                screen = null
            }
        }
    }

    fun openScreen(new: Screen) {
        screen = new
    }

}