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
import dev.isxander.particlesenhanced.utils.mc
import dev.isxander.particlesenhanced.utils.uieditor.modifyGui
import dev.isxander.xanderlib.XanderLib
import dev.isxander.xanderlib.utils.HttpsUtils
import dev.isxander.xanderlib.utils.json.BetterJsonObject
import gg.essential.api.EssentialAPI
import gg.essential.universal.UDesktop
import net.minecraft.client.gui.GuiButton
import net.minecraft.client.gui.GuiOptions
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import org.bundleproject.libversion.Version
import java.io.File
import java.net.URI

@Mod(
    modid = ID,
    name = NAME,
    version = VERSION_STR,
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

        ParticlesEnhancedConfig.preload()

        if (ParticlesEnhancedConfig.checkUpdates && !EssentialAPI.getMinecraftUtil().isDevelopment()) {
            val latest = BetterJsonObject(HttpsUtils.getString("https://dl.isxander.dev/mods/particlesenhanced/assets/metadata.json")).getObj("latest")

            if (VERSION < Version(latest.optInt("major"), latest.optInt("minor"), latest.optInt("patch"))) {
                EssentialAPI.getNotifications().push("Particles Enhanced", "There is an update available. Click here to go to the download page.") {
                    UDesktop.browse(URI.create("https://hypixel.net/threads/4416018/"))
                }
            }
        }

        ParticlesEnhancedCommand("particlesenhanced").register()
        ParticlesEnhancedCommand("particlemod").register()
        ParticlesEnhancedCommand("particle").register()
        ParticlesEnhancedCommand("particles").register()
        ParticlesEnhancedCommand("penhanced").register()
        ParticlesEnhancedCommand("pem").register()

        MinecraftForge.EVENT_BUS.register(AlwaysCriticals)
        MinecraftForge.EVENT_BUS.register(AlwaysSharpness)

        modifyGui<GuiOptions> {
            initGui {
                post { buttonList ->
                    buttonList.removeIf { it.id == 107 }
                    buttonList.add(GuiButton(838573356, width / 2 + 5, height / 6 + 72 - 6, 150, 20, "Particles Enhanced..."))
                }
            }
            actionPerformed {
                post { _, button ->
                    if (button.id != 838573356) return@post
                    mc.displayGuiScreen(ParticlesEnhancedConfig.gui())
                }
            }
        }
    }

}