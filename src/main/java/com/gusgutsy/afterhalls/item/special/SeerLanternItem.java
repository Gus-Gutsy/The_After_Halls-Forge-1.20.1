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
    private float nearbyPredicate = 1.0F;

    public SeerLanternItem(Properties pProperties) {
        super(pProperties);
        nearbyPredicate = 1.0F;
    }

    public void inventoryTick(@NotNull ItemStack pStack, Level pLevel, @NotNull Entity pEntity, int pItemSlot, boolean pIsSelected) {
        if (!pLevel.isClientSide) {
            // Get the list of monsters within 16 of the player.
            List<Monster> nearMonsters = pLevel.getEntitiesOfClass(Monster.class, pEntity.getBoundingBox().inflate(16));


            if (!nearMonsters.isEmpty()){
                double nearestDistance = Double.MAX_VALUE; // Hold the max value for comparison later.

                for (Monster monster : nearMonsters){
                    // For each monster get the distance from entity to monster, comparing each for the closest one.
                    double dist = pEntity.getEyePosition().distanceTo(monster.position());
                    nearestDistance = Math.min(dist, nearestDistance);
                }

                // Decide on which predicate based on the closest monster.
                // 0-5: close, 5-10: medium, 10-16: far , none: off
                nearbyPredicate = (float) nearestDistance / 16.0f;
            }
            else {
                // Reflects none: off
                nearbyPredicate = 1.0F;
            }
            // Write float to change lantern texture.
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
