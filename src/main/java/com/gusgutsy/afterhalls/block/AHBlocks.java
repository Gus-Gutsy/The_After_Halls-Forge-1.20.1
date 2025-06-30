package com.gusgutsy.afterhalls.block;

import com.gusgutsy.afterhalls.AfterHalls;
import com.gusgutsy.afterhalls.block.special.CryingIronBlock;
import com.gusgutsy.afterhalls.item.AHItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.function.Supplier;

public class AHBlocks {
    // Set up blocks to be added when FML is ready.
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, AfterHalls.MOD_ID);

    //--- Block registering ---//
    // Crying Iron
    public static final RegistryObject<Block> CRYING_IRON_BLOCK = registerBlock("crying_iron_block",
            () -> new CryingIronBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    // Silver
    public static final RegistryObject<Block> SILVER_ORE = registerBlock("silver_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GOLD_ORE)));
    // Bleakwood
    public static final RegistryObject<Block> BLEAKWOOD_PLANKS= registerBlock("bleakwood_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> BLEAKWOOD_STAIRS= registerBlock("bleakwood_stairs",
            () -> new StairBlock(() -> AHBlocks.BLEAKWOOD_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)));
    public static final RegistryObject<Block> BLEAKWOOD_SLAB= registerBlock("bleakwood_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)));
    public static final RegistryObject<Block> BLEAKWOOD_BUTTON= registerBlock("bleakwood_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON),
                    BlockSetType.OAK, 10, true));
    public static final RegistryObject<Block> BLEAKWOOD_PRESSURE_PLATE= registerBlock("bleakwood_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE),
                    BlockSetType.OAK));
    public static final RegistryObject<Block> BLEAKWOOD_FENCE= registerBlock("bleakwood_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));
    public static final RegistryObject<Block> BLEAKWOOD_FENCE_GATE= registerBlock("bleakwood_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE),
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE));
    public static final RegistryObject<Block> BLEAKWOOD_DOOR= registerBlock("bleakwood_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR).noOcclusion(), BlockSetType.OAK));
    public static final RegistryObject<Block> BLEAKWOOD_TRAPDOOR= registerBlock("bleakwood_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR).noOcclusion(), BlockSetType.OAK));


    // Registers a block with ITEMS so it can appear in inventory system
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return AHItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    // Registers a block to BLOCKS and ITEMS
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    // Registers BLOCKS to the passed event bus
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
