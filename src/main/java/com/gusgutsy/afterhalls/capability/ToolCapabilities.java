package com.gusgutsy.afterhalls.capability;


import com.gusgutsy.afterhalls.custom.DurabilityTracker;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class ToolCapabilities {
    public static final Capability<DurabilityTracker> DURABILITY_TRACKER =
            CapabilityManager.get(new CapabilityToken<>() {});
}
