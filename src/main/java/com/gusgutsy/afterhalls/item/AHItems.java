package com.gusgutsy.afterhalls.item;

import com.gusgutsy.afterhalls.AfterHalls;
import com.gusgutsy.afterhalls.item.cryingiron.*;
import com.gusgutsy.afterhalls.item.special.*;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AHItems {
    // Set up items to be added when FML is ready.
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AfterHalls.MOD_ID);

    //--- Item registering ---//
    // Crying Iron
    public static final RegistryObject<Item> CURSED_RAW_IRON = ITEMS.register("cursed_raw_iron",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRYING_IRON_INGOT = ITEMS.register("crying_iron_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRYING_IRON_AXE = ITEMS.register("crying_iron_axe",
            () -> new CryingIronAxeItem(AHToolTiers.CRYING_IRON, 6, -3.1f, new Item.Properties()));
    public static final RegistryObject<Item> CRYING_IRON_HOE = ITEMS.register("crying_iron_hoe",
            () -> new CryingIronHoeItem(AHToolTiers.CRYING_IRON, -1, -2.0f, new Item.Properties()));
    public static final RegistryObject<Item> CRYING_IRON_PICKAXE = ITEMS.register("crying_iron_pickaxe",
            () -> new CryingIronPickaxeItem(AHToolTiers.CRYING_IRON,1, -2.8f, new Item.Properties()));
    public static final RegistryObject<Item> CRYING_IRON_SWORD = ITEMS.register("crying_iron_sword",
            () -> new CryingIronSwordItem(AHToolTiers.CRYING_IRON, 3, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> CRYING_IRON_SHOVEL = ITEMS.register("crying_iron_shovel",
            () -> new CryingIronShovelItem(AHToolTiers.CRYING_IRON, 1.5f, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> CRYING_IRON_HELMET = ITEMS.register("crying_iron_helmet",
            () -> new CryingIronArmorItem(AHArmorMaterials.CRYING_IRON, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> CRYING_IRON_CHESTPLATE = ITEMS.register("crying_iron_chestplate",
            () -> new CryingIronArmorItem(AHArmorMaterials.CRYING_IRON, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> CRYING_IRON_LEGGINGS = ITEMS.register("crying_iron_leggings",
            () -> new CryingIronArmorItem(AHArmorMaterials.CRYING_IRON, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> CRYING_IRON_BOOTS = ITEMS.register("crying_iron_boots",
            () -> new CryingIronArmorItem(AHArmorMaterials.CRYING_IRON, ArmorItem.Type.BOOTS, new Item.Properties()));
    // Silver
    public static final RegistryObject<Item> RAW_SILVER = ITEMS.register("raw_silver",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot",
            () -> new Item(new Item.Properties()));
    // Special Items
    public static final RegistryObject<Item> SEER_LANTERN = ITEMS.register("seer_lantern",
            () -> new SeerLanternItem(new Item.Properties()));
    public static final RegistryObject<Item> ROTBRAND_STAFF = ITEMS.register("rotbrand_staff",
            ()-> new RotbrandStaffItem(new Item.Properties().durability(20).setNoRepair()));
    // Food Items
    public static final RegistryObject<Item> POMEGRANATE = ITEMS.register("pomegranate",
            () -> new Item(new Item.Properties().food(AHFoods.POMEGRANATE)));

    // Registers ITEMS to the passed event bus
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
