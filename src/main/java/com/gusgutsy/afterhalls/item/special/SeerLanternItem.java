package com.gusgutsy.afterhalls.item.special;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SeerLanternItem extends Item {
    private float nearbyPredicate;

    public SeerLanternItem(Properties pProperties) {
        super(pProperties);
        nearbyPredicate = 1.0F;
    }

    public void inventoryTick(@NotNull ItemStack pStack, Level pLevel, @NotNull Entity pEntity, int pItemSlot, boolean pIsSelected) {
        if (!pLevel.isClientSide) {
            List<Monster> nearMonsters = pLevel.getEntitiesOfClass(Monster.class, pEntity.getBoundingBox().inflate(16));

            if (!nearMonsters.isEmpty()){
                double nearestDistance = Double.MAX_VALUE;

                for (Monster monster : nearMonsters){
                    double dist = pEntity.getEyePosition().distanceTo(monster.position());

                    nearestDistance = Math.min(dist, nearestDistance);
                }

                nearbyPredicate = (float) nearestDistance / 16.0f;
            }
            else {
                nearbyPredicate = 1.0F;
            }
            pStack.getOrCreateTag().putFloat("nearby", nearbyPredicate);
            }
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.afterhalls.seer_lantern"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        // Prevent animation so that the lantern smoothly changes textures
        return false;
    }

}
