package com.gusgutsy.afterhalls.block.special;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class CryingIronBlock extends Block {
    public CryingIronBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void playerDestroy(Level pLevel, Player pPlayer, BlockPos pPos, BlockState pState, @Nullable BlockEntity pBlockEntity, ItemStack pTool) {
        // TODO: Modify sound that's played
        Random rand = new Random();
        int soundCheck = rand.nextInt(100);

        if (soundCheck > 90) {
            BlockPos randomBlockPos = new BlockPos(pPos.getX() + (rand.nextInt(10) - 5), pPos.getY(), pPos.getZ() + (rand.nextInt(10) - 5));

            pLevel.playSound(null, randomBlockPos, SoundEvents.ALLAY_DEATH, SoundSource.BLOCKS, 0.5f,2f);
        }

        super.playerDestroy(pLevel, pPlayer, pPos, pState, pBlockEntity, pTool);
    }

}
