package com.gusgutsy.afterhalls.item;

import com.gusgutsy.afterhalls.AfterHalls;
import com.gusgutsy.afterhalls.block.AHBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class AHCreativeModeTabs {

    // Set up tabs to be added when FML is ready.
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AfterHalls.MOD_ID);

    // Register tabs
    public static final RegistryObject<CreativeModeTab> AFTER_HALLS_TAB = CREATIVE_MODE_TABS.register("after_halls_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(AHItems.CRYING_IRON_INGOT.get()))
                    .title(Component.translatable("creativetab.after_halls_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        //Items for tab
                        output.accept(AHItems.CURSED_RAW_IRON.get());
                        output.accept(AHItems.RAW_SILVER.get());
                        output.accept(AHItems.CRYING_IRON_INGOT.get());
                        output.accept(AHItems.CRYING_IRON_HELMET.get());
                        output.accept(AHItems.CRYING_IRON_CHESTPLATE.get());
                        output.accept(AHItems.CRYING_IRON_LEGGINGS.get());
                        output.accept(AHItems.CRYING_IRON_BOOTS.get());
                        output.accept(AHItems.POMEGRANATE.get());
                        output.accept(AHItems.SEER_LANTERN.get());

                        //Blocks for tab
                        output.accept(AHBlocks.CRYING_IRON_BLOCK.get());
                        output.accept(AHBlocks.SILVER_ORE.get());
                        output.accept(AHBlocks.BLEAKWOOD_PLANKS.get());
                        output.accept(AHBlocks.BLEAKWOOD_STAIRS.get());
                        output.accept(AHBlocks.BLEAKWOOD_SLAB.get());
                        output.accept(AHBlocks.BLEAKWOOD_BUTTON.get());
                        output.accept(AHBlocks.BLEAKWOOD_PRESSURE_PLATE.get());
                        output.accept(AHBlocks.BLEAKWOOD_FENCE.get());
                        output.accept(AHBlocks.BLEAKWOOD_FENCE_GATE.get());
                        output.accept(AHBlocks.BLEAKWOOD_DOOR.get());
                        output.accept(AHBlocks.BLEAKWOOD_TRAPDOOR.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
