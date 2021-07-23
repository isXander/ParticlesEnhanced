/*
 * Copyright (c) [2021 - 2021] isXander
 *
 * All rights reserved!
 */

package dev.isxander.particlesenhanced

import dev.isxander.particlesenhanced.config.ParticlesEnhancedConfig
import dev.isxander.particlesenhanced.modules.overrides.AlwaysCriticals
import dev.isxander.particlesenhanced.modules.overrides.AlwaysSharpness
import dev.isxander.particlesenhanced.utils.GuiHandler
import gg.essential.vigilance.Vigilance
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager.literal
import net.minecraft.client.MinecraftClient
import java.io.File

object ParticlesEnhanced : ClientModInitializer {

    val DATA_DIR = File(mc.runDirectory, "config/particles-enhanced")

    override fun onInitializeClient() {
        DATA_DIR.mkdirs()

        Vigilance.initialize()
        ParticlesEnhancedConfig.preload()

        ClientCommandManager.DISPATCHER.register(literal("particlemod").executes {
            ParticlesEnhancedConfig.gui()?.let { it1 -> GuiHandler.openScreen(it1) }

            return@executes 0
        })

        AlwaysCriticals; AlwaysSharpness
    }

}

val mc: MinecraftClient = MinecraftClient.getInstance()