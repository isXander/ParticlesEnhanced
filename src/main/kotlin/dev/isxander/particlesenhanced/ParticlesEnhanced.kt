/*
 * Copyright (c) [2021 - 2021] isXander
 *
 * All rights reserved!
 */

package dev.isxander.particlesenhanced

import dev.isxander.particlesenhanced.command.ParticlesEnhancedCommand
import dev.isxander.particlesenhanced.config.ParticlesEnhancedConfig
import dev.isxander.particlesenhanced.modules.overrides.AlwaysCriticals
import dev.isxander.particlesenhanced.modules.overrides.AlwaysSharpness
import dev.isxander.xanderlib.XanderLib
import dev.isxander.xanderlib.ui.editor.AbstractGuiModifier
import dev.isxander.xanderlib.utils.Constants.mc
import gg.essential.api.EssentialAPI
import gg.essential.vigilance.Vigilance
import net.minecraft.client.gui.GuiButton
import net.minecraft.client.gui.GuiOptions
import net.minecraft.client.gui.GuiScreen
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import java.io.File

@Mod(
    modid = ParticlesEnhancedInfo.ID,
    name = ParticlesEnhancedInfo.NAME,
    version = ParticlesEnhancedInfo.VERSION_FULL,
    acceptedMinecraftVersions = "[1.8.9]",
    clientSideOnly = true,
    modLanguageAdapter = "gg.essential.api.utils.KotlinAdapter"
)
object ParticlesEnhanced {

    val DATA_DIR = File(mc.mcDataDir, "config/particles-enhanced")

    @Mod.EventHandler
    fun onFMLInit(event: FMLInitializationEvent) {
        XanderLib.getInstance().initPhase()

        DATA_DIR.mkdirs()

        Vigilance.initialize()
        ParticlesEnhancedConfig.preload()

        ParticlesEnhancedCommand("particlesenhanced").register()
        ParticlesEnhancedCommand("particlemod").register()
        ParticlesEnhancedCommand("particle").register()
        ParticlesEnhancedCommand("particles").register()
        ParticlesEnhancedCommand("penhanced").register()
        ParticlesEnhancedCommand("pem").register()

        MinecraftForge.EVENT_BUS.register(AlwaysCriticals)
        MinecraftForge.EVENT_BUS.register(AlwaysSharpness)

        XanderLib.getInstance().guiEditor.addModifier(GuiOptions::class.java, object : AbstractGuiModifier {
            override fun onInitGuiPost(screen: GuiScreen, buttonList: MutableList<GuiButton>) {
                buttonList.removeIf { it.id == 107 }
                buttonList.add(GuiButton(838573356, screen.width / 2 + 5, screen.height / 6 + 72 - 6, 150, 20, "Particles Enhanced..."))
            }
            override fun onActionPerformedPost(
                screen: GuiScreen,
                buttonList: MutableList<GuiButton>,
                button: GuiButton
            ) {
                if (button.id != 838573356) return
                mc.displayGuiScreen(ParticlesEnhancedConfig.gui())
            }
        })
    }

}