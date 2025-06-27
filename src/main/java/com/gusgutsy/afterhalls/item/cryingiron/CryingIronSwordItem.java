package com.gusgutsy.afterhalls.item.cryingiron;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class CryingIronSwordItem extends SwordItem implements ICryingIron {

    public CryingIronSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack pStack, @NotNull LivingEntity pTarget, @NotNull LivingEntity pAttacker){
        System.out.println("[ATTACK] Before: " + pStack.getDamageValue());
        applyLifeSteal(pTarget, pAttacker);
        boolean result = super.hurtEnemy(pStack, pTarget, pAttacker);
        System.out.println("[ATTACK] After: " + pStack.getDamageValue());
        return result;
    }

    @Override
    public void onInventoryTick(ItemStack pStack, Level pLevel, Player pPlayer, int pSlotIndex, int pSelectedIndex){
        increaseDurability(pLevel, pStack);
        super.onInventoryTick(pStack, pLevel, pPlayer, pSlotIndex, pSelectedIndex);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        if (slotChanged) {
            return true;
        }
        else {
            return oldStack.getItem() != newStack.getItem();
        }
    }
}
