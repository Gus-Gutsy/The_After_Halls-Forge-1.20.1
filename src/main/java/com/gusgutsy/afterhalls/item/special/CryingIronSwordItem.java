package com.gusgutsy.afterhalls.item.special;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class CryingIronSwordItem extends SwordItem implements ICryingIron {
    public CryingIronSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker){
        applyUndeadLifeSteal(pTarget, pAttacker);
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
