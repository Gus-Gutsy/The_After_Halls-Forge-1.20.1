package com.gusgutsy.afterhalls;

import com.gusgutsy.afterhalls.block.AHBlocks;
import com.gusgutsy.afterhalls.handler.VillagerConversionHandler;
import com.gusgutsy.afterhalls.item.AHCreativeModeTabs;
import com.gusgutsy.afterhalls.item.AHItems;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(AfterHalls.MOD_ID)
public class AfterHalls {

    public static final String MOD_ID = "afterhalls";
    private static final Logger LOGGER = LogUtils.getLogger();

    public AfterHalls() {
        // Setup event bus to pick up the kids (AKA the Deferred Registers).
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Tickets please!
        AHCreativeModeTabs.register(modEventBus);
        AHItems.register(modEventBus);
        AHBlocks.register(modEventBus);


        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(ClientModEvents.class);
        MinecraftForge.EVENT_BUS.register(VillagerConversionHandler.class);
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(AHItems.CURSED_RAW_IRON);
            event.accept(AHItems.CRYING_IRON_INGOT);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

}
