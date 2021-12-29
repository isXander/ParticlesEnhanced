package dev.isxander.particlesenhanced.utils.uieditor

import net.minecraft.client.gui.GuiScreen
import net.minecraftforge.client.event.GuiScreenEvent.*
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.eventhandler.EventPriority
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import kotlin.reflect.KClass

inline fun <reified T : GuiScreen> modifyGui(modifier: GuiModifier.() -> Unit) =
    GuiEditor.addModifier(T::class, GuiModifier().apply(modifier))

object GuiEditor {
    private val modifiers = mutableMapOf<KClass<out GuiScreen>, MutableList<GuiModifier>>()
    
    private var mouseX = 0
    private var mouseY = 0
    private var partialTicks = 0f

    init {
        MinecraftForge.EVENT_BUS.register(this)
    }

    fun <T : GuiScreen> addModifier(guiClass: KClass<T>, modifier: GuiModifier) {
        modifiers.computeIfAbsent(guiClass) { mutableListOf() }.add(modifier)
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    fun onGuiInitPre(event: InitGuiEvent.Pre) {
        val mods = modifiers[event.gui::class] ?: return

        for (mod in mods) {
            mod.initGui.pre(event.gui, event.buttonList)
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    fun onGuiInitPost(event: InitGuiEvent.Post) {
        val mods = modifiers[event.gui::class] ?: return
        
        for (mod in mods) {
            mod.initGui.post(event.gui, event.buttonList)
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    fun onActionPerformedPre(event: ActionPerformedEvent.Pre) {
        val mods = modifiers[event.gui::class] ?: return
        
        for (mod in mods) {
            mod.actionPerformed.pre(event.gui, event.buttonList, event.button)
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    fun onActionPerformedPost(event: ActionPerformedEvent.Post) {
        val mods = modifiers[event.gui::class] ?: return
        
        for (mod in mods) {
            mod.actionPerformed.post(event.gui, event.buttonList, event.button)
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    fun onDrawScreenPre(event: DrawScreenEvent.Pre) {
        val mods = modifiers[event.gui::class] ?: return
        
        for (mod in mods) {
            this.mouseX = event.mouseX
            this.mouseY = event.mouseY
            this.partialTicks = event.renderPartialTicks
            mod.drawScreen.pre(event.gui, mouseX, mouseY, partialTicks)
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    fun onDrawScreenPost(event: DrawScreenEvent.Post) {
        val mods = modifiers[event.gui::class] ?: return
        
        for (mod in mods) {
            mod.drawScreen.post(event.gui, mouseX, mouseY, partialTicks)
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    fun onBackgroundDraw(event: BackgroundDrawnEvent) {
        val mods = modifiers[event.gui::class] ?: return
        
        for (mod in mods) {
            mod.drawBackground(event.gui, event.mouseX, event.mouseY)
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    fun onKeyboardInputPre(event: KeyboardInputEvent.Pre) {
        val mods = modifiers[event.gui::class] ?: return
        
        for (mod in mods) {
            mod.keyboardInput.pre(event.gui)
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    fun onKeyboardInputPost(event: KeyboardInputEvent.Post) {
        val mods = modifiers[event.gui::class] ?: return
        
        for (mod in mods) {
            mod.keyboardInput.post(event.gui)
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    fun onMouseInputPre(event: MouseInputEvent.Pre) {
        val mods = modifiers[event.gui::class] ?: return
        
        for (mod in mods) {
            mod.mouseInput.pre(event.gui)
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    fun onMouseInputPost(event: MouseInputEvent.Post) {
        val mods = modifiers[event.gui::class] ?: return
        
        for (mod in mods) {
            mod.mouseInput.post(event.gui)
        }
    }
}