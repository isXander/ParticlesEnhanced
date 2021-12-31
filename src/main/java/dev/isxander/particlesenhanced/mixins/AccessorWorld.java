package dev.isxander.particlesenhanced.mixins;

import net.minecraft.world.IWorldAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(World.class)
public interface AccessorWorld {
    @Accessor
    List<IWorldAccess> getWorldAccesses();
}
