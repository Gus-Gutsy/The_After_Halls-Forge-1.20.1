package com.gusgutsy.afterhalls.item;

import com.gusgutsy.afterhalls.AfterHalls;
import com.gusgutsy.afterhalls.tags.AHTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class AHToolTiers {

    public static final Tier CRYING_IRON = TierSortingRegistry.registerTier(
            new ForgeTier(2, 127, 7, 2, 17,
                    AHTags.Blocks.NEEDS_CRYING_IRON_TOOL, () -> Ingredient.of(AHItems.CRYING_IRON_INGOT.get())),
            new ResourceLocation(AfterHalls.MOD_ID, "crying_iron"), List.of(Tiers.IRON), List.of(Tiers.DIAMOND));
}
