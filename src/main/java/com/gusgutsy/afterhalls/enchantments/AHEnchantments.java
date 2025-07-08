package com.gusgutsy.afterhalls.enchantments;

import com.gusgutsy.afterhalls.AfterHalls;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AHEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, AfterHalls.MOD_ID);


    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}
