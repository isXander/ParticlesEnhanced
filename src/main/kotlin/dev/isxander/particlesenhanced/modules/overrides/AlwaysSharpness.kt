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
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.*
import net.minecraft.entity.damage.DamageSource
import net.minecraft.particle.ParticleTypes
import net.minecraft.util.ActionResult

object AlwaysSharpness {

    init {
        AttemptAttackEntityCallback.EVENT.register(object : AttemptAttackEntityCallback {
            override fun interact(attacker: ClientPlayerEntity, target: Entity): ActionResult {
                if (!ParticlesEnhancedConfig.alwaysSharp)
                    return ActionResult.PASS

                if (ParticlesEnhancedConfig.checkInvulnerable && target.isInvulnerableTo(DamageSource.player(attacker)))
                    return ActionResult.PASS

                var modifier =
                    if (target is LivingEntity) EnchantmentHelper.getAttackDamage(attacker.mainHandStack, target.group)
                    else EnchantmentHelper.getAttackDamage(attacker.mainHandStack, EntityGroup.DEFAULT)
                modifier *= attacker.getAttackCooldownProgress(0.5f)

                if (modifier <= 0f) {
                    mc.particleManager.addEmitter(target, ParticleTypes.ENCHANTED_HIT);
                }

                return ActionResult.PASS
            }
        })
    }

}