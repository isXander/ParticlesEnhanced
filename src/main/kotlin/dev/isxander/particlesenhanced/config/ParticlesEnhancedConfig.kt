package dev.isxander.particlesenhanced.config

import dev.isxander.particlesenhanced.ParticlesEnhanced
import gg.essential.vigilance.Vigilant
import gg.essential.vigilance.data.Property
import gg.essential.vigilance.data.PropertyType
import java.io.File

object ParticlesEnhancedConfig : Vigilant(File(ParticlesEnhanced.DATA_DIR, "config.toml"), "Particles") {

    init { initialize() }

    @Property(
        type = PropertyType.SWITCH,
        name = "Fade",
        description = "Make particles fade rather than just disappearing.",
        category = "General"
    )
    var fade = true

    @Property(
        type = PropertyType.SLIDER,
        name = "Minimum Transparency",
        description = "Rather than fading to nothing fade to this value.",
        category = "General",
        max = 100,
        min = 0
    )
    var minFadeTransparency = 0

    init { addDependency("minFadeTransparency", "fade") }

}