package com.gusgutsy.afterhalls;

import com.gusgutsy.afterhalls.item.AHItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@Mod.EventBusSubscriber(modid = AfterHalls.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemProperties.register(
                    AHItems.SEER_LANTERN.get(),
                    new ResourceLocation(AfterHalls.MOD_ID, "nearby"),
                    (stack, level, entity, seed) -> {
                        float distancePredicate = 0.0f;
                        CompoundTag tag = stack.getTag();
                        if (tag != null && tag.contains("nearby", Tag.TAG_FLOAT)) {
                            distancePredicate = tag.getFloat("nearby");
                        }
                        return distancePredicate;
                    }
            );
        });
    }
}