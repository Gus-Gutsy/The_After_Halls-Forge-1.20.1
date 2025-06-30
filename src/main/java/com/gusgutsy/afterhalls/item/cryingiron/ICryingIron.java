package com.gusgutsy.afterhalls.item.cryingiron;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface ICryingIron {
    int REPAIR_TICK_INTERVAL = 300;
    int REPAIR_AMOUNT = 1;
    float HEAL_AMOUNT = 2.0f;

    default void applyLifeSteal(LivingEntity target, LivingEntity attacker) {
        if (target.getMobType() == MobType.UNDEAD && attacker != null) {
            attacker.heal(HEAL_AMOUNT);
            // Generate a soul particle to show life steal
            ((ServerLevel) target.level()).sendParticles(
                    ParticleTypes.SOUL,
                    target.getX(), target.getY() + 1.0d, target.getZ(),
                    1, 0.2d, 0.5d, 0.2d, 0.03f
            );
        }
    }

    default void increaseDurability(Level pLevel, ItemStack pStack){
        if (!pLevel.isClientSide && pStack.isDamaged()) {
            // Get or create the RepairTicks tag, then find out the number of ticks before repair.
            CompoundTag tag = pStack.getOrCreateTag();
            int repairTimerTicks = tag.getInt("RepairTicks");
            repairTimerTicks++;

            // If it's time to repair, fix by Repair amount (should be 1)
            if (repairTimerTicks >= REPAIR_TICK_INTERVAL) { // 15 seconds
                pStack.setDamageValue(Math.max(0, pStack.getDamageValue() - REPAIR_AMOUNT));
                repairTimerTicks = 0;
            }

            // put the new incremented value
            tag.putInt("RepairTicks", repairTimerTicks);
        }
    }
}
