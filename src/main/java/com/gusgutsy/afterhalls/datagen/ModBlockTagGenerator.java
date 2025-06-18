package com.gusgutsy.afterhalls.datagen;

import com.gusgutsy.afterhalls.AfterHalls;
import com.gusgutsy.afterhalls.block.AHBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, AfterHalls.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        // Provide in the following format:
        // this.tag(AHTags.Blocks.CUSTOM_ITEM_VALUES).add(ModBlocks.ORE_BLOCK_NAME.get()).addTag(Tags.Blocks.ORES);
        // Source: https://youtu.be/enzKJWq0vNI?si=ENLcha9Rw1YhEjtq

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(AHBlocks.CRYING_IRON_BLOCK.get())
                .add(AHBlocks.SILVER_ORE.get());

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(AHBlocks.CRYING_IRON_BLOCK.get())
                .add(AHBlocks.SILVER_ORE.get());

        this.tag(BlockTags.FENCES).add(AHBlocks.BLEAKWOOD_FENCE.get());

        this.tag(BlockTags.FENCE_GATES).add(AHBlocks.BLEAKWOOD_FENCE_GATE.get());
    }
}
