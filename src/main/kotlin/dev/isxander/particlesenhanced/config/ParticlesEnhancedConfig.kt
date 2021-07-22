/*
 * Copyright (c) [2021 - 2021] isXander
 *
 * All rights reserved!
 */

package dev.isxander.particlesenhanced.config

import dev.isxander.particlesenhanced.ParticlesEnhanced
import gg.essential.vigilance.Vigilant
import gg.essential.vigilance.data.Property
import gg.essential.vigilance.data.PropertyType
import java.io.File

object ParticlesEnhancedConfig : Vigilant(File(ParticlesEnhanced.DATA_DIR, "config.toml"), "Particles Enhanced") {

    init { initialize() }

    @Property(
        type = PropertyType.SWITCH,
        name = "Fade",
        description = "Make particles fade rather than just disappearing.",
        category = "Aesthetics",
        subcategory = "Fade"
    )
    var fade = true

    @Property(
        type = PropertyType.SLIDER,
        name = "Minimum Transparency",
        description = "Rather than fading to nothing fade to this value.",
        category = "Aesthetics",
        subcategory = "Fade",
        max = 100,
        min = 0
    )
    var minFadeTransparency = 0

    @Property(
        type = PropertyType.SWITCH,
        name = "Check Invulnerability",
        description = "Before showing the critical or sharpness particles, check if the player can be hit (e.g. isn't in creative mode)",
        category = "Aesthetics",
        subcategory = "Overrides"
    )
    var checkInvulnerable = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Always Show Criticals",
        description = "Every time you hit an entity, critical particles are spawned.",
        category = "Aesthetics",
        subcategory = "Overrides"
    )
    var alwaysCrit = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Always Show Sharpness",
        description = "Every time you hit an entity, sharpness particles are spawned.",
        category = "Aesthetics",
        subcategory = "Overrides"
    )
    var alwaysSharp = false

    @Property(
        type = PropertyType.SLIDER,
        name = "Critical Multiplier",
        description = "How many critical particles you want to see.",
        category = "Aesthetics",
        subcategory = "Multipliers",
        max = 20,
        min = 0
    )
    var critMultiplier = 1

    @Property(
        type = PropertyType.SLIDER,
        name = "Sharpness Multiplier",
        description = "How many sharpness particles you want to see.",
        category = "Aesthetics",
        subcategory = "Multipliers",
        max = 20,
        min = 0
    )
    var sharpMultiplier = 1

}