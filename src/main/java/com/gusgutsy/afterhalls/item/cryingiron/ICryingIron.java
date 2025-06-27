package com.gusgutsy.afterhalls.item.cryingiron;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface ICryingIron {
    int REPAIR_TICK_INTERVAL = 300;
    int REPAIR_AMOUNT = 1;

    default void applyLifeSteal(LivingEntity target, LivingEntity attacker) {
        if (target.getMobType() == MobType.UNDEAD && attacker != null) {
            attacker.addEffect(new MobEffectInstance(MobEffects.HEAL, 1));
        }
    }

    default void increaseDurability(Level pLevel, ItemStack pStack){
        if (!pLevel.isClientSide && pStack.isDamaged()) {
            CompoundTag tag = pStack.getOrCreateTag();
            int repairTimerTicks = tag.getInt("RepairTicks");
            repairTimerTicks++;

            if (repairTimerTicks >= REPAIR_TICK_INTERVAL) { // 15 seconds
                pStack.setDamageValue(Math.max(0, pStack.getDamageValue() - REPAIR_AMOUNT));
                repairTimerTicks = 0;
            }

            tag.putInt("RepairTicks", repairTimerTicks);
        }
    }
}
