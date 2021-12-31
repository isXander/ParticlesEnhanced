package dev.isxander.particlesenhanced.mixins;

import dev.isxander.particlesenhanced.config.ParticlesEnhancedConfig;
import dev.isxander.particlesenhanced.modules.multipliers.ParticleMultiplier;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.IWorldAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(EntityArrow.class)
public class MixinEntityArrow {
    @Redirect(method = "onUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnParticle(Lnet/minecraft/util/EnumParticleTypes;DDDDDD[I)V"))
    public void bowOverride(World world, EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double xOffset, double yOffset, double zOffset, int... arguments) {
        ParticlesEnhancedConfig cfg = ParticlesEnhancedConfig.INSTANCE;
        ParticleMultiplier multiplier = ParticleMultiplier.INSTANCE;

        List<IWorldAccess> worldAccesses = ((AccessorWorld) world).getWorldAccesses();
        int particleId = particleType.getParticleID();
        boolean ignoreRange = particleType.getShouldIgnoreRange();
        if (cfg.getOverrideCriticalBow()) {
            multiplier.replaceAndSpawn(worldAccesses, particleId, ignoreRange, xCoord, yCoord, zCoord, xOffset, yOffset, zOffset, 0, arguments);
        } else {
            multiplier.spawnParticle(worldAccesses, particleId, ignoreRange, xCoord, yCoord, zCoord, xOffset, yOffset, zOffset, 0, arguments);
        }
    }
}
