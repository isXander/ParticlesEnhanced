package dev.isxander.particlesenhanced.mixins;

import dev.isxander.particlesenhanced.event.AttemptAttackEntityCallback;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public abstract class MixinClientPlayerEntity extends MixinPlayerEntity {

    @Override
    public void attack(Entity entity, CallbackInfo ci) {
        AttemptAttackEntityCallback.Companion.getEVENT().invoker().interact((ClientPlayerEntity) (Object) this, entity);
    }
}
