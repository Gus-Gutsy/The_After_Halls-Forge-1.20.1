package com.gusgutsy.afterhalls.datagen.loot;

import com.gusgutsy.afterhalls.block.AHBlocks;
import com.gusgutsy.afterhalls.item.AHItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        // Create custom ore drops based off of the functions found in BlockLootSubProvider. They give a
        // good template for these. Otherwise, use existing functions.

        // This method allows the block to drop different items than itself.
        this.add(AHBlocks.SILVER_ORE.get(),
                block -> createOreDrop(AHBlocks.SILVER_ORE.get(), AHItems.RAW_SILVER.get()));

        // This method allows the block to drop the item version of itself when it's broken.
        this.dropSelf(AHBlocks.CRYING_IRON_BLOCK.get());

        this.dropSelf(AHBlocks.BLEAKWOOD_PLANKS.get());
        this.dropSelf(AHBlocks.BLEAKWOOD_STAIRS.get());
        this.dropSelf(AHBlocks.BLEAKWOOD_BUTTON.get());
        this.dropSelf(AHBlocks.BLEAKWOOD_PRESSURE_PLATE.get());
        this.dropSelf(AHBlocks.BLEAKWOOD_FENCE.get());
        this.dropSelf(AHBlocks.BLEAKWOOD_FENCE_GATE.get());
        this.dropSelf(AHBlocks.BLEAKWOOD_TRAPDOOR.get());

        this.add(AHBlocks.BLEAKWOOD_SLAB.get(),
                block -> createSlabItemTable(AHBlocks.BLEAKWOOD_SLAB.get()));
        this.add(AHBlocks.BLEAKWOOD_DOOR.get(),
                block -> createDoorTable(AHBlocks.BLEAKWOOD_DOOR.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return AHBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
