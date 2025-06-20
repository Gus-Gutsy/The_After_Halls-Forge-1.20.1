package com.gusgutsy.afterhalls.tags;

import com.gusgutsy.afterhalls.AfterHalls;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class AHTags {
    // When creating a new tag, add it below, and make a JSON file in data.afterhalls.blocks or data.afterhalls.items following format:
    // { "values": [ "item", "names", "here" ] }
    public static class Blocks {
        public static final TagKey<Block> NEEDS_CRYING_IRON_TOOL = tag("needs_crying_iron_tool");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(AfterHalls.MOD_ID, name));
        }
    }
    public static class Items {

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(AfterHalls.MOD_ID, name));
        }
    }
}
