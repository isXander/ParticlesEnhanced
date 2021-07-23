/*
 * Copyright (c) [2021 - 2021] isXander
 *
 * All rights reserved!
 */

package dev.isxander.particlesenhanced.modules.overrides

import dev.isxander.particlesenhanced.config.ParticlesEnhancedConfig
import dev.isxander.particlesenhanced.event.AttemptAttackEntityCallback
import dev.isxander.particlesenhanced.mc
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.particle.ParticleTypes
import net.minecraft.util.ActionResult

object AlwaysCriticals {

    init {
        AttemptAttackEntityCallback.EVENT.register(object : AttemptAttackEntityCallback {
            override fun interact(attacker: ClientPlayerEntity, target: Entity): ActionResult {
                if (!ParticlesEnhancedConfig.alwaysCrit)
                    return ActionResult.PASS

                if (ParticlesEnhancedConfig.checkInvulnerable && target.isInvulnerableTo(DamageSource.player(attacker)))
                    return ActionResult.PASS

                val criticalHit = attacker.getAttackCooldownProgress(0.5f) > 0.9f
                        && attacker.fallDistance > 0.0F
                        && !attacker.isOnGround
                        && !attacker.isClimbing
                        && !attacker.isTouchingWater
                        && !attacker.hasStatusEffect(StatusEffects.BLINDNESS)
                        && !attacker.hasVehicle()
                        && target is LivingEntity

                if (!criticalHit) {
                    mc.particleManager.addEmitter(target, ParticleTypes.CRIT);
                }

                return ActionResult.PASS
            }
        })
    }

}