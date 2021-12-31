/*
 * Copyright (c) [2021 - 2021] isXander
 *
 * All rights reserved!
 */

package dev.isxander.particlesenhanced.mixins;

import dev.isxander.particlesenhanced.modules.multipliers.ParticleMultiplier;
import net.minecraft.world.IWorldAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(World.class)
public class MixinWorld {
    @Shadow protected List<IWorldAccess> worldAccesses;

    @Inject(method = "spawnParticle(IZDDDDDD[I)V", at = @At("HEAD"), cancellable = true)
    private void spawnParticle(int particleID, boolean ignoreRange, double xCoord, double yCoord, double zCoord, double xOffset, double yOffset, double zOffset, int[] arguments, CallbackInfo ci) {
        if (ParticleMultiplier.INSTANCE.replaceAndSpawn(worldAccesses, particleID, ignoreRange, xCoord, yCoord, zCoord, xOffset, yOffset, zOffset, 1.0, arguments)) ci.cancel();
    }
}
