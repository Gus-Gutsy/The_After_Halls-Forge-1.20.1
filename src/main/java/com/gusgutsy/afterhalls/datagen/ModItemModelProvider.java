package com.gusgutsy.afterhalls.datagen;

import com.gusgutsy.afterhalls.AfterHalls;
import com.gusgutsy.afterhalls.block.AHBlocks;
import com.gusgutsy.afterhalls.item.AHItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AfterHalls.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(AHItems.CURSED_RAW_IRON);
        simpleItem(AHItems.CRYING_IRON_INGOT);
        handheldItem(AHItems.CRYING_IRON_AXE);
        handheldItem(AHItems.CRYING_IRON_HOE);
        handheldItem(AHItems.CRYING_IRON_PICKAXE);
        handheldItem(AHItems.CRYING_IRON_SHOVEL);
        handheldItem(AHItems.CRYING_IRON_SWORD);
        simpleItem(AHItems.CRYING_IRON_BOOTS);
        simpleItem(AHItems.CRYING_IRON_LEGGINGS);
        simpleItem(AHItems.CRYING_IRON_CHESTPLATE);
        simpleItem(AHItems.CRYING_IRON_HELMET);

        simpleItem(AHItems.RAW_SILVER);
        simpleItem(AHItems.SILVER_INGOT);

        simpleBlockItem(AHBlocks.BLEAKWOOD_DOOR);

        evenSimplerBlockItem(AHBlocks.BLEAKWOOD_STAIRS);
        evenSimplerBlockItem(AHBlocks.BLEAKWOOD_SLAB);
        evenSimplerBlockItem(AHBlocks.BLEAKWOOD_PRESSURE_PLATE);
        evenSimplerBlockItem(AHBlocks.BLEAKWOOD_FENCE_GATE);

        fenceItem(AHBlocks.BLEAKWOOD_FENCE, AHBlocks.BLEAKWOOD_PLANKS);
        buttonItem(AHBlocks.BLEAKWOOD_BUTTON, AHBlocks.BLEAKWOOD_PLANKS);
        trapdoorItem(AHBlocks.BLEAKWOOD_TRAPDOOR);

        simpleItem(AHItems.POMEGRANATE);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(AfterHalls.MOD_ID, "item/" + item.getId().getPath()));
    }

    public void evenSimplerBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(AfterHalls.MOD_ID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(AfterHalls.MOD_ID,"item/" + item.getId().getPath()));
    }

    public void trapdoorItem(RegistryObject<Block> block) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath() + "_bottom"));
    }

    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  new ResourceLocation(AfterHalls.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  new ResourceLocation(AfterHalls.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  new ResourceLocation(AfterHalls.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(AfterHalls.MOD_ID,"item/" + item.getId().getPath()));
    }
}
