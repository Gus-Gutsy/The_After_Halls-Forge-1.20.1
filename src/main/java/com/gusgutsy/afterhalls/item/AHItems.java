package com.gusgutsy.afterhalls.item;

import com.gusgutsy.afterhalls.AfterHalls;
import com.gusgutsy.afterhalls.item.special.SeerLanternItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AHItems {
    // Set up items to be added when FML is ready.
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AfterHalls.MOD_ID);

    // Item registering //
    // Crying Iron
    public static final RegistryObject<Item> CURSED_RAW_IRON = ITEMS.register("cursed_raw_iron",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRYING_IRON_INGOT = ITEMS.register("crying_iron_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRYING_IRON_HELMET = ITEMS.register("crying_iron_helmet",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRYING_IRON_CHESTPLATE = ITEMS.register("crying_iron_chestplate",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRYING_IRON_LEGGINGS = ITEMS.register("crying_iron_leggings",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRYING_IRON_BOOTS = ITEMS.register("crying_iron_boots",
            () -> new Item(new Item.Properties()));

    // Silver
    public static final RegistryObject<Item> RAW_SILVER = ITEMS.register("raw_silver",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot",
            () -> new Item(new Item.Properties()));

    // Food Items
    public static final RegistryObject<Item> POMEGRANATE = ITEMS.register("pomegranate",
            () -> new Item(new Item.Properties().food(AHFoods.POMEGRANATE)));

    // Special Items
    public static final RegistryObject<Item> SEER_LANTERN = ITEMS.register("seer_lantern",
            () -> new SeerLanternItem(new Item.Properties()));



    // Registers ITEMS to the passed event bus
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
