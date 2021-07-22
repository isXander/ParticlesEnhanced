/*
 * Copyright (c) [2021 - 2021] isXander
 *
 * All rights reserved!
 */

package dev.isxander.particlesenhanced.modules.overrides

import dev.isxander.particlesenhanced.config.ParticlesEnhancedConfig
import dev.isxander.xanderlib.utils.Constants
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.EnumCreatureAttribute
import net.minecraft.util.DamageSource
import net.minecraft.util.EnumParticleTypes
import net.minecraftforge.event.entity.player.AttackEntityEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

object AlwaysSharpness {

    @SubscribeEvent
    fun onAttack(event: AttackEntityEvent) {
        if (!ParticlesEnhancedConfig.alwaysSharp)
            return

        if (ParticlesEnhancedConfig.checkInvulnerable && event.target.isEntityInvulnerable(DamageSource.causePlayerDamage(event.entityPlayer)))
            return

        val target = event.target

        val modifier = if (target is EntityLivingBase) {
            EnchantmentHelper.getModifierForCreature(event.entityLiving.heldItem, target.creatureAttribute)
        } else {
            EnchantmentHelper.getModifierForCreature(event.entityLiving.heldItem, EnumCreatureAttribute.UNDEFINED)
        }

        if (modifier <= 0f) {
            Constants.mc.effectRenderer.emitParticleAtEntity(event.target, EnumParticleTypes.CRIT_MAGIC)
        }
    }

}