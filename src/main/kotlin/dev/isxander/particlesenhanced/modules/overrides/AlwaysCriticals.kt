/*
 * Copyright (c) [2021 - 2021] isXander
 *
 * All rights reserved!
 */

package dev.isxander.particlesenhanced.modules.overrides

import dev.isxander.particlesenhanced.config.ParticlesEnhancedConfig
import dev.isxander.xanderlib.event.PacketEvent
import dev.isxander.xanderlib.utils.Constants.mc
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.network.play.server.S19PacketEntityStatus
import net.minecraft.potion.Potion
import net.minecraft.util.EnumParticleTypes
import net.minecraftforge.event.entity.player.AttackEntityEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent


object AlwaysCriticals {

    private var attacker: EntityPlayer? = null
    private var targetId = -1

    @SubscribeEvent
    fun onPacket(event: PacketEvent.Incoming) {
        if (!ParticlesEnhancedConfig.checkInvulnerable) return

        if (event.packet is S19PacketEntityStatus) {
            val packet = event.packet as S19PacketEntityStatus
            if (packet.opCode.toInt() != 2) return

            val target = packet.getEntity(mc.theWorld) ?: return
            if (attacker != null && targetId == target.entityId) {
                doCritical(attacker!!, target)
                attacker = null
                targetId = -1
            }
        }
    }

    @SubscribeEvent
    fun onAttack(event: AttackEntityEvent) {
        if (ParticlesEnhancedConfig.checkInvulnerable) {
            if (event.entityPlayer.entityId == mc.thePlayer.entityId) {
                attacker = event.entityPlayer
                targetId = event.target.entityId
            }
        } else {
            doCritical(event.entityPlayer, event.target)
        }
    }
    
    private fun doCritical(attacker: EntityPlayer, target: Entity) {
        if (!ParticlesEnhancedConfig.alwaysCrit)
            return

        val criticalHit = attacker.fallDistance > 0.0F
                && !attacker.onGround
                && !attacker.isOnLadder
                && !attacker.isInWater
                && !attacker.isPotionActive(Potion.blindness)
                && attacker.ridingEntity == null
                && target is EntityLivingBase

        if (!criticalHit) {
            mc.effectRenderer.emitParticleAtEntity(target, EnumParticleTypes.CRIT)
        }
    }

}