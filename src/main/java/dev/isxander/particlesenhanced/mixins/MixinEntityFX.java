/*
 * Copyright (c) [2021 - 2021] isXander
 *
 * All rights reserved!
 */

package dev.isxander.particlesenhanced.mixins;

import dev.isxander.particlesenhanced.config.ParticlesEnhancedConfig;
import dev.isxander.xanderlib.utils.MathUtils;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityFX.class)
public abstract class MixinEntityFX {
    @Shadow public abstract void setAlphaF(float alpha);
    @Shadow public abstract float getAlpha();

    @Shadow protected int particleAge;
    @Shadow protected int particleMaxAge;

    @Inject(method = "renderParticle", at = @At("HEAD"))
    private void injectOpacity(WorldRenderer worldRendererIn, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ, CallbackInfo ci) {
        ParticlesEnhancedConfig cfg = ParticlesEnhancedConfig.INSTANCE;

        if (!cfg.getFade()) return;

        float lifeSpan = MathUtils.getPercent(particleAge, particleMaxAge * (cfg.getMinFadeTransparency() / 100f), particleMaxAge);
        if (lifeSpan > ParticlesEnhancedConfig.INSTANCE.getFadeOutStart()) {
            float alpha = 1 - lifeSpan + ParticlesEnhancedConfig.INSTANCE.getFadeOutStart();
            if (getAlpha() != alpha) setAlphaF(alpha);
        }
    }

}
