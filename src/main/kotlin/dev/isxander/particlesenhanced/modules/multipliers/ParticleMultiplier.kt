package dev.isxander.particlesenhanced.modules.multipliers

import dev.isxander.particlesenhanced.config.ParticlesEnhancedConfig
import net.minecraft.util.EnumParticleTypes
import net.minecraft.world.IWorldAccess

object ParticleMultiplier {
    fun replaceAndSpawn(worldAccesses: List<IWorldAccess>, particleID: Int, ignoreRange: Boolean, x: Double, y: Double, z: Double, xOffset: Double, yOffset: Double, zOffset: Double, randomness: Double = 1.0, vararg arguments: Int): Boolean {
        val cfg = ParticlesEnhancedConfig

        return when (EnumParticleTypes.getParticleFromId(particleID)) {
            EnumParticleTypes.CRIT -> {
                repeat(cfg.critMultiplier) {
                    spawnParticle(worldAccesses, cfg.critParticleType, ignoreRange, x, y, z, xOffset, yOffset, zOffset, randomness, *arguments)
                }

                true
            }
            EnumParticleTypes.CRIT_MAGIC -> {
                repeat(cfg.sharpMultiplier) {
                    spawnParticle(worldAccesses, cfg.sharpParticleType, ignoreRange, x, y, z, xOffset, yOffset, zOffset, randomness, *arguments)
                }

                true
            }
            else -> false
        }
    }

    fun spawnParticle(worldAccesses: List<IWorldAccess>, _particleID: Int, ignoreRange: Boolean, x: Double, y: Double, z: Double, xOffset: Double, yOffset: Double, zOffset: Double, randomness: Double = 1.0, vararg _arguments: Int) {
        for (worldAccess in worldAccesses) {
            var particleID = _particleID
            var arguments = _arguments
            if (particleID == 42) { // custom blood particle
                particleID = EnumParticleTypes.BLOCK_CRACK.particleID
                arguments = arguments.copyOf(arguments.size + 1);
                arguments[arguments.size - 1] = 152; // redstone block
            }

            val modX = x - randomness / 2.0 + Math.random() * randomness
            val modY = y - randomness / 2.0 + Math.random() * randomness
            val modZ = z - randomness / 2.0 + Math.random() * randomness

            worldAccess.spawnParticle(particleID, ignoreRange, modX, modY, modZ, xOffset, yOffset, zOffset, *arguments);
        }
    }
}