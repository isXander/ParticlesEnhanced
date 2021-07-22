/*
 * Copyright (c) [2021 - 2021] isXander
 *
 * All rights reserved!
 */

package dev.isxander.particlesenhanced.modules.overrides

import dev.isxander.particlesenhanced.config.ParticlesEnhancedConfig
import dev.isxander.xanderlib.utils.Constants.mc
import net.minecraft.entity.EntityLivingBase
import net.minecraft.potion.Potion
import net.minecraft.util.DamageSource
import net.minecraft.util.EnumParticleTypes
import net.minecraftforge.event.entity.player.AttackEntityEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

object AlwaysCriticals {

    @SubscribeEvent
    fun onAttack(event: AttackEntityEvent) {
        if (!ParticlesEnhancedConfig.alwaysCrit)
            return

        if (ParticlesEnhancedConfig.checkInvulnerable && event.target.isEntityInvulnerable(DamageSource.causePlayerDamage(event.entityPlayer)))
            return

        val criticalHit = event.entityPlayer.fallDistance > 0.0F
                && !event.entityPlayer.onGround
                && !event.entityPlayer.isOnLadder
                && !event.entityPlayer.isInWater
                && !event.entityPlayer.isPotionActive(Potion.blindness)
                && event.entityPlayer.ridingEntity == null
                && event.target is EntityLivingBase

        if (!criticalHit) {
            mc.effectRenderer.emitParticleAtEntity(event.target, EnumParticleTypes.CRIT)
        }
    }

}