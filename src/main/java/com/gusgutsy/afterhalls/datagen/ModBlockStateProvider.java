package com.gusgutsy.afterhalls.datagen;

import com.gusgutsy.afterhalls.AfterHalls;
import com.gusgutsy.afterhalls.block.AHBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, AfterHalls.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(AHBlocks.CRYING_IRON_BLOCK);
        blockWithItem(AHBlocks.SILVER_ORE);

        blockWithItem(AHBlocks.BLEAKWOOD_PLANKS);

        stairsBlock((StairBlock) AHBlocks.BLEAKWOOD_STAIRS.get(), blockTexture(AHBlocks.BLEAKWOOD_PLANKS.get()));
        slabBlock((SlabBlock) AHBlocks.BLEAKWOOD_SLAB.get(), blockTexture(AHBlocks.BLEAKWOOD_PLANKS.get()), blockTexture(AHBlocks.BLEAKWOOD_PLANKS.get()));

        buttonBlock((ButtonBlock) AHBlocks.BLEAKWOOD_BUTTON.get(), blockTexture(AHBlocks.BLEAKWOOD_PLANKS.get()));
        pressurePlateBlock((PressurePlateBlock) AHBlocks.BLEAKWOOD_PRESSURE_PLATE.get(), blockTexture(AHBlocks.BLEAKWOOD_PLANKS.get()));

        fenceBlock((FenceBlock) AHBlocks.BLEAKWOOD_FENCE.get(), blockTexture(AHBlocks.BLEAKWOOD_PLANKS.get()));
        fenceGateBlock((FenceGateBlock) AHBlocks.BLEAKWOOD_FENCE_GATE.get(), blockTexture(AHBlocks.BLEAKWOOD_PLANKS.get()));

        doorBlockWithRenderType((DoorBlock) AHBlocks.BLEAKWOOD_DOOR.get(), modLoc("block/bleakwood_door_bottom"),
                modLoc("block/bleakwood_door_top"), "cutout");
        trapdoorBlockWithRenderType((TrapDoorBlock) AHBlocks.BLEAKWOOD_TRAPDOOR.get(), modLoc("block/bleakwood_trapdoor"),
                true, "cutout");
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
