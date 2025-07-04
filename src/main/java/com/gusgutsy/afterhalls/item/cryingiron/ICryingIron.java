package com.gusgutsy.afterhalls.item.cryingiron;

import com.gusgutsy.afterhalls.capability.ToolCapabilities;
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
            pStack.getCapability(ToolCapabilities.DURABILITY_TRACKER).ifPresent(tracker -> {
                tracker.tick();
                if (tracker.shouldHeal()) {
                    pStack.setDamageValue(Math.max(0, pStack.getDamageValue() - 1));
                    tracker.reset();
                }
            });
        }
    }
}
