package com.gusgutsy.afterhalls.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class AHFoods {
    public static final FoodProperties POMEGRANATE = new FoodProperties.Builder().nutrition(6)
            .saturationMod(0.4f).effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 600),1f).alwaysEat().build();
}
