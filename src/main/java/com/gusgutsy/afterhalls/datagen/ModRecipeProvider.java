package com.gusgutsy.afterhalls.datagen;

import com.gusgutsy.afterhalls.AfterHalls;
import com.gusgutsy.afterhalls.block.AHBlocks;
import com.gusgutsy.afterhalls.item.AHItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> CURSED_IRON_SMELTABLE = List.of(AHItems.CURSED_RAW_IRON.get());

    private static final List<ItemLike> SILVER_SMELTABLES = List.of(
            AHItems.RAW_SILVER.get(),
            AHBlocks.SILVER_ORE.get()
    );

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AHItems.CURSED_RAW_IRON.get())
                .requires(Items.RAW_IRON)
                .requires(Items.SOUL_SAND)
                .unlockedBy(getHasName(Items.RAW_IRON), has(Items.RAW_IRON))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AHBlocks.CRYING_IRON_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', AHItems.CRYING_IRON_INGOT.get())
                .unlockedBy(getHasName(AHItems.CRYING_IRON_INGOT.get()), has(AHItems.CRYING_IRON_INGOT.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AHItems.CRYING_IRON_INGOT.get(), 9)
                .requires(AHBlocks.CRYING_IRON_BLOCK.get())
                .unlockedBy(getHasName(AHItems.CRYING_IRON_INGOT.get()), has(AHItems.CRYING_IRON_INGOT.get()))
                .save(pWriter);

        simpleArmorSetRecipeBuilder(pWriter, AHItems.CRYING_IRON_INGOT, AHItems.CRYING_IRON_HELMET, AHItems.CRYING_IRON_CHESTPLATE,
                AHItems.CRYING_IRON_LEGGINGS, AHItems.CRYING_IRON_BOOTS);

        oreSmelting(pWriter, CURSED_IRON_SMELTABLE, RecipeCategory.MISC, AHItems.CRYING_IRON_INGOT.get(), 0.25f, 200, "crying_iron");
        oreBlasting(pWriter, CURSED_IRON_SMELTABLE, RecipeCategory.MISC, AHItems.CRYING_IRON_INGOT.get(), 0.25f, 100, "crying_iron");
        oreSmelting(pWriter, SILVER_SMELTABLES, RecipeCategory.MISC, AHItems.SILVER_INGOT.get(), 0.25f, 200, "silver");
        oreBlasting(pWriter, SILVER_SMELTABLES, RecipeCategory.MISC, AHItems.SILVER_INGOT.get(), 0.25f, 100, "silver");
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, AfterHalls.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }

    private static void simpleArmorSetRecipeBuilder(Consumer<FinishedRecipe> pWriter,
                                                    RegistryObject<Item> pIngot,
                                                    RegistryObject<Item> pHelmet,
                                                    RegistryObject<Item> pChestpiece,
                                                    RegistryObject<Item> pLeggings,
                                                    RegistryObject<Item> pBoots) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, pHelmet.get())
                .pattern("XXX")
                .pattern("X X")
                .pattern("   ")
                .define('X', pIngot.get())
                .unlockedBy(getHasName(pIngot.get()), has(pIngot.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, pChestpiece.get())
                .pattern("X X")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', pIngot.get())
                .unlockedBy(getHasName(pIngot.get()), has(pIngot.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, pLeggings.get())
                .pattern("XXX")
                .pattern("X X")
                .pattern("X X")
                .define('X', pIngot.get())
                .unlockedBy(getHasName(pIngot.get()), has(pIngot.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, pBoots.get())
                .pattern("   ")
                .pattern("X X")
                .pattern("X X")
                .define('X', pIngot.get())
                .unlockedBy(getHasName(pIngot.get()), has(pIngot.get()))
                .save(pWriter);

    }
}
