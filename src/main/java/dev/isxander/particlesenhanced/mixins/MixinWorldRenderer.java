/*
 * Copyright (c) [2021 - 2021] isXander
 *
 * All rights reserved!
 */

package dev.isxander.particlesenhanced.mixins;

import dev.isxander.particlesenhanced.config.ParticlesEnhancedConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.ParticlesMode;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public abstract class MixinWorldRenderer {

    @Shadow @Final private MinecraftClient client;

    @Shadow protected abstract ParticlesMode getRandomParticleSpawnChance(boolean bl);

    @Inject(method = "addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V", at = @At("HEAD"), cancellable = true)
    private void spawnParticle(ParticleEffect particleEffect, double x, double y, double z, double velocityX, double velocityY, double velocityZ, CallbackInfo ci) {
        boolean override = true;

        if (particleEffect.getType() == ParticleTypes.CRIT) {
            for (int i = 0; i < ParticlesEnhancedConfig.INSTANCE.getCritMultiplier(); i++)
                randomSpawn(particleEffect, x, y, z, velocityX, velocityY, velocityZ);
        } if (particleEffect.getType() == ParticleTypes.ENCHANTED_HIT) {
            for (int i = 0; i < ParticlesEnhancedConfig.INSTANCE.getSharpMultiplier(); i++)
                randomSpawn(particleEffect, x, y, z, velocityX, velocityY, velocityZ);
        } else {
            override = false;
        }

        if (override) ci.cancel();
    }

    private void randomSpawn(ParticleEffect particleEffect, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        boolean bl = particleEffect.getType().shouldAlwaysSpawn();

        x = (x - 0.5) + Math.random();
        y = (y - 0.5) + Math.random();
        z = (z - 0.5) + Math.random();

        Camera camera = this.client.gameRenderer.getCamera();
        if (this.client != null && camera.isReady() && this.client.particleManager != null) {
            ParticlesMode particlesMode = this.getRandomParticleSpawnChance(false);
            if (bl) {
                this.client.particleManager.addParticle(particleEffect, x, y, z, velocityX, velocityY, velocityZ);
            } else if (camera.getPos().squaredDistanceTo(x, y, z) <= 1024.0D) {
                if (particlesMode != ParticlesMode.MINIMAL) {
                    this.client.particleManager.addParticle(particleEffect, x, y, z, velocityX, velocityY, velocityZ);
                }
            }
        }
    }

}
