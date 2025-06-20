package com.gusgutsy.afterhalls.item.special;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;

public interface ICryingIron {
    default void applyUndeadLifeSteal(LivingEntity target, LivingEntity attacker) {
        if (target.getMobType() == MobType.UNDEAD && attacker != null) {
            target.addEffect(new MobEffectInstance(MobEffects.HEAL));
            attacker.addEffect(new MobEffectInstance(MobEffects.HEAL));
        }
    }
}
