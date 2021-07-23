package dev.isxander.particlesenhanced.event

import net.fabricmc.fabric.api.event.Event
import net.fabricmc.fabric.api.event.EventFactory
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ActionResult

interface AttemptAttackEntityCallback {

    fun interact(attacker: ClientPlayerEntity, target: Entity): ActionResult

    companion object {

        val EVENT: Event<AttemptAttackEntityCallback> = EventFactory.createArrayBacked(AttemptAttackEntityCallback::class.java) { listeners ->
            object : AttemptAttackEntityCallback {
                override fun interact(attacker: ClientPlayerEntity, target: Entity): ActionResult {
                    for (listener in listeners) {
                        val result = listener.interact(attacker, target)

                        if (result != ActionResult.PASS) {
                            return result
                        }
                    }
                    return ActionResult.PASS
                }
            }
        }

    }

}