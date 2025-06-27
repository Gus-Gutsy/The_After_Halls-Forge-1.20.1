package com.gusgutsy.afterhalls.item.special;

import com.mojang.logging.LogUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

public class RotbrandStaffItem extends Item {
    private static final Logger LOGGER = LogUtils.getLogger();
    public RotbrandStaffItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack pStack, Player pPlayer, @NotNull LivingEntity pInteractionTarget, @NotNull InteractionHand pUsedHand) {
        if (!pPlayer.level().isClientSide) {
            if (pInteractionTarget.getType() == EntityType.VILLAGER) {
                CompoundTag tag = pInteractionTarget.getPersistentData();
                tag.putInt("rotbrandConversionTime", 20);
                tag.putBoolean("rotbrandConvertToZombie", true);

                pStack.hurtAndBreak(1, pPlayer, (p) -> p.broadcastBreakEvent(pUsedHand));
                pPlayer.getCooldowns().addCooldown(this, 20);
            }
        }
        return InteractionResult.sidedSuccess(pPlayer.level().isClientSide);
    }

}
