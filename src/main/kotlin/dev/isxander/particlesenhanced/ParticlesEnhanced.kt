package dev.isxander.particlesenhanced

import dev.isxander.particlesenhanced.command.ParticlesEnhancedCommand
import dev.isxander.particlesenhanced.config.ParticlesEnhancedConfig
import dev.isxander.particlesenhanced.modules.overrides.AlwaysCriticals
import dev.isxander.particlesenhanced.modules.overrides.AlwaysSharpness
import dev.isxander.xanderlib.utils.Constants.mc
import gg.essential.vigilance.Vigilance
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
    }

}