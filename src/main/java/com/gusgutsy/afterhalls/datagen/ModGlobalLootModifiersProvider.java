package com.gusgutsy.afterhalls.datagen;

import com.gusgutsy.afterhalls.AfterHalls;
import com.gusgutsy.afterhalls.item.AHItems;
import com.gusgutsy.afterhalls.loot.AddItemModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import org.checkerframework.checker.units.qual.A;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, AfterHalls.MOD_ID);
    }

    @Override
    protected void start() {
        add("jet_from_coal_ore", new AddItemModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.COAL_ORE).build(),
                LootItemRandomChanceCondition.randomChance(0.025f).build()
        }, AHItems.JET.get()));

        add("opal_from_zombie", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("entities/witch"))
                        .or(new LootTableIdCondition.Builder(new ResourceLocation("chests/buried_treasure")))
                        .or(new LootTableIdCondition.Builder(new ResourceLocation("chests/pillager_outpost")))
                        .or(new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_treasure")))
                        .or(new LootTableIdCondition.Builder(new ResourceLocation("chests/shipwreck_treasure")))
                        .or(new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")))
                        .build()
        }, AHItems.OPAL.get()));
    }
}
