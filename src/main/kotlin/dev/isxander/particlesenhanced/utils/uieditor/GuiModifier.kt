package dev.isxander.particlesenhanced.utils.uieditor

import net.minecraft.client.gui.GuiButton
import net.minecraft.client.gui.GuiScreen

class GuiModifier {
    val initGui: PrePostContext<GuiScreen.(MutableList<GuiButton>) -> Unit> = PrePostContext { {} }
    val actionPerformed: PrePostContext<GuiScreen.(MutableList<GuiButton>, GuiButton) -> Unit> = PrePostContext { { _, _ -> } }
    val drawScreen: PrePostContext<GuiScreen.(Int, Int, Float) -> Unit> = PrePostContext { { _, _, _ -> } }
    var drawBackground: GuiScreen.(Int, Int) -> Unit = { _, _ -> }
        private set
    val keyboardInput: PrePostContext<GuiScreen.() -> Unit> = PrePostContext { {} }
    val mouseInput: PrePostContext<GuiScreen.() -> Unit> = PrePostContext { {} }

    fun initGui(block: PrePostContext<GuiScreen.(MutableList<GuiButton>) -> Unit>.() -> Unit) {
        initGui.apply(block)
    }

    fun actionPerformed(block: PrePostContext<GuiScreen.(MutableList<GuiButton>, GuiButton) -> Unit>.() -> Unit) {
        actionPerformed.apply(block)
    }

    fun drawScreen(block: PrePostContext<GuiScreen.(Int, Int, Float) -> Unit>.() -> Unit) {
        drawScreen.apply(block)
    }

    fun drawBackground(block: GuiScreen.(Int, Int) -> Unit) {
        drawBackground = block
    }

    fun keyboardInput(block: PrePostContext<GuiScreen.() -> Unit>.() -> Unit) {
        keyboardInput.apply(block)
    }

    fun mouseInput(block: PrePostContext<GuiScreen.() -> Unit>.() -> Unit) {
        mouseInput.apply(block)
    }
}