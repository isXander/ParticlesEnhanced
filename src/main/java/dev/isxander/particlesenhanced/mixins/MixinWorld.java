/*
 * Copyright (c) [2021 - 2021] isXander
 *
 * All rights reserved!
 */

package dev.isxander.particlesenhanced.mixins;

import dev.isxander.particlesenhanced.config.ParticlesEnhancedConfig;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.IWorldAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.List;

@Mixin(World.class)
public class MixinWorld {

    @Shadow protected List<IWorldAccess> worldAccesses;

    @Inject(method = "spawnParticle(IZDDDDDD[I)V", at = @At("HEAD"), cancellable = true)
    private void spawnParticle(int particleID, boolean p_175720_2_, double xCoord, double yCoord, double zCoord, double xOffset, double yOffset, double zOffset, int[] p_175720_15_, CallbackInfo ci) {
        ParticlesEnhancedConfig cfg = ParticlesEnhancedConfig.INSTANCE;

        boolean override = true;

        switch (EnumParticleTypes.getParticleFromId(particleID)) {
            case CRIT:
                for (int i = 0; i < cfg.getCritMultiplier(); i++)
                    randomSpawn(cfg.getCritParticleType(), p_175720_2_, xCoord, yCoord, zCoord, xOffset, yOffset, zOffset, p_175720_15_);
                break;
            case CRIT_MAGIC:
                for (int i = 0; i < cfg.getSharpMultiplier(); i++)
                    randomSpawn(cfg.getSharpParticleType(), p_175720_2_, xCoord, yCoord, zCoord, xOffset, yOffset, zOffset, p_175720_15_);
                break;

            default:
                override = false;
                break;
        }

        if (override) ci.cancel();
    }

    private void randomSpawn(int particleID, boolean p_175720_2_, double xCoord, double yCoord, double zCoord, double xOffset, double yOffset, double zOffset, int[] arguments) {
        for (IWorldAccess worldAccess : this.worldAccesses) {
            if (particleID == 42) { // custom blood particle
                particleID = EnumParticleTypes.BLOCK_CRACK.getParticleID();
                arguments = Arrays.copyOf(arguments, arguments.length + 1);
                arguments[arguments.length - 1] = 152; // redstone block
            }
            worldAccess.spawnParticle(particleID, p_175720_2_, (xCoord - 0.5) + Math.random(), (yCoord - 0.5) + Math.random(), (zCoord - 0.5) + Math.random(), xOffset, yOffset, zOffset, arguments);
        }
    }

}
