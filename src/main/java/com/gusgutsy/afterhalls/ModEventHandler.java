package com.gusgutsy.afterhalls;

import com.gusgutsy.afterhalls.capability.ToolCapabilities;
import com.gusgutsy.afterhalls.item.cryingiron.ICryingIron;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "afterhalls")
public class ModEventHandler {
    @SubscribeEvent
    public static void attachItemCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
        if (event.getObject().getItem() instanceof ICryingIron) {
            event.addCapability(
                    new ResourceLocation("afterhalls", "durability_tracker"),
                    new ICapabilityProvider() {
                        private final DurabilityTracker tracker = new DurabilityTracker();
                        private final LazyOptional<DurabilityTracker> optional = LazyOptional.of(() -> tracker);

                        @Override
                        public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
                            return cap == ToolCapabilities.DURABILITY_TRACKER ? optional.cast() : LazyOptional.empty();
                        }
                    }
            );
        }
    }
}