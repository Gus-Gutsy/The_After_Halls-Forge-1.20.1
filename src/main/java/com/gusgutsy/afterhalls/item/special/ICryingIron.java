package com.gusgutsy.afterhalls.item.special;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface ICryingIron {
    final int DURABILITY_REFRESH_TICKS = 300;

    default void applyLifeSteal(LivingEntity target, LivingEntity attacker) {
        if (target.getMobType() == MobType.UNDEAD && attacker != null) {
            attacker.addEffect(new MobEffectInstance(MobEffects.HEAL, 1));
        }
    }

    default void increaseDurability(Level pLevel, ItemStack pStack){
        if (!pLevel.isClientSide && pStack.isDamaged()) {
            CompoundTag tag = pStack.getOrCreateTag();
            int ticks = tag.getInt("DurabilityTicks");
            ticks++;

            if (ticks >= 600) { // 30 seconds
                pStack.setDamageValue(pStack.getDamageValue() - 1); // heal 1 point
                ticks = 0;
            }

            tag.putInt("DurabilityTicks", ticks);
        }
    }
}
