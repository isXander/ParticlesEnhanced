/*
 * Copyright (c) [2021 - 2021] isXander
 *
 * All rights reserved!
 */

package dev.isxander.particlesenhanced.mixins;

import dev.isxander.particlesenhanced.config.ParticlesEnhancedConfig;
import dev.isxander.xanderlib.utils.MathUtils;
import net.minecraft.client.particle.Particle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Particle.class)
public abstract class MixinParticle {

    @Shadow protected float colorAlpha;

    @Shadow protected int age;
    @Shadow protected int maxAge;

    @Inject(method = "tick", at = @At("HEAD"))
    private void injectOpacity(CallbackInfo ci) {
        ParticlesEnhancedConfig cfg = ParticlesEnhancedConfig.INSTANCE;

        if (!cfg.getFade()) return;

        float lifeSpan = MathUtils.getPercent(age, maxAge * (cfg.getMinFadeTransparency() / 100f), maxAge);
        if (lifeSpan > ParticlesEnhancedConfig.INSTANCE.getFadeOutStart()) {
            float alpha = 1 - lifeSpan + ParticlesEnhancedConfig.INSTANCE.getFadeOutStart();
            if (colorAlpha != alpha) colorAlpha = alpha;
        }
    }

}
