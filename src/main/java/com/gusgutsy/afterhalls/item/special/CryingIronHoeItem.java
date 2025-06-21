package com.gusgutsy.afterhalls.item.special;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

public class CryingIronHoeItem extends HoeItem implements ICryingIron {

    public CryingIronHoeItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker){
        applyLifeSteal(pTarget, pAttacker);
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

    @Override
    public void onInventoryTick(ItemStack pStack, Level pLevel, Player pPlayer, int pSlotIndex, int pSelectedIndex){
        increaseDurability(pLevel, pStack);
        super.onInventoryTick(pStack, pLevel, pPlayer, pSlotIndex, pSelectedIndex);
    }
}
