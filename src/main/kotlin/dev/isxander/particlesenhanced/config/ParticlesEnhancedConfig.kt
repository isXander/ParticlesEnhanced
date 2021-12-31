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
import net.minecraft.util.EnumParticleTypes
import java.io.File

object ParticlesEnhancedConfig : Vigilant(File(ParticlesEnhanced.DATA_DIR, "config.toml"), "Particles Enhanced") {
    @Property(
        type = PropertyType.SELECTOR,
        name = "Critical Particle Type",
        description = "Change what type of particle appears when you get a critical hit.",
        category = "Aesthetics",
        subcategory = "Overrides",
        options = ["Normal Explosion", "Large Explosion", "Huge Explosion", "Firework Spark", "Water Bubble", "Water Splash", "Water Wake", "Suspended", "Suspended Depth", "Critical", "Sharpness", "Normal Smoke", "Large Smoke", "Spell", "Instant Spell", "Mob Spell", "Ambient Mob Spell", "Witch Spell", "Water Drip", "Lava Drip", "Angry Villager", "Happy Villager", "Town Aura", "Note", "Portal", "Enchantment Table", "Flame", "Lava", "Footstep", "Cloud", "Redstone", "Snowball", "Shovel Snow", "Slime", "Heart", "Barrier", "Item Break", "Block Break", "Block Dust", "Water Drop", "Take Item", "Mop Appearance", "Blood"]
    )
    var critParticleType = 9

    @Property(
        type = PropertyType.SELECTOR,
        name = "Sharpness Particle Type",
        description = "Change what type of particle appears when you get a sharpness hit.",
        category = "Aesthetics",
        subcategory = "Overrides",
        options = ["Normal Explosion", "Large Explosion", "Huge Explosion", "Firework Spark", "Water Bubble", "Water Splash", "Water Wake", "Suspended", "Suspended Depth", "Critical", "Sharpness", "Normal Smoke", "Large Smoke", "Spell", "Instant Spell", "Mob Spell", "Ambient Mob Spell", "Witch Spell", "Water Drip", "Lava Drip", "Angry Villager", "Happy Villager", "Town Aura", "Note", "Portal", "Enchantment Table", "Flame", "Lava", "Footstep", "Cloud", "Redstone", "Snowball", "Shovel Snow", "Slime", "Heart", "Barrier", "Item Break", "Block Break", "Block Dust", "Water Drop", "Take Item", "Mop Appearance", "Blood"]
    )
    var sharpParticleType = 10

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
        type = PropertyType.SWITCH,
        name = "Override Critical Bow Arrows",
        description = "When you fire a full charge hit with a bow, critical particles are spawned. Override with your desired particle?",
        category = "Aesthetics",
        subcategory = "Overrides"
    )
    var overrideCriticalBow = true


    @Property(
        type = PropertyType.SWITCH,
        name = "Fade",
        description = "Make particles fade rather than just disappearing.",
        category = "Aesthetics",
        subcategory = "Fade"
    )
    var fade = true

    @Property(
        type = PropertyType.PERCENT_SLIDER,
        name = "Fade Out Start",
        description = "How far into the lifespan of the particle before it starts to fade.",
        category = "Aesthetics",
        subcategory = "Fade"
    )
    var fadeOutStart = 0.5f

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

    @Property(
        type = PropertyType.SWITCH,
        name = "Check for Updates",
        description = "Connect to the internet to check if you are using the latest version on ParticlesEnhanced.",
        category = "Connectivity"
    )
    var checkUpdates = true

    init { initialize() }
}