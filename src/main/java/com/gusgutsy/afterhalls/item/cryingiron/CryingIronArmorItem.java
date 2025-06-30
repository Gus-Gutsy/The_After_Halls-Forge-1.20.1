package com.gusgutsy.afterhalls.item.cryingiron;

import com.google.common.collect.ImmutableMap;
import com.gusgutsy.afterhalls.item.AHArmorMaterials;
import com.mojang.logging.LogUtils;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.slf4j.Logger;

import java.util.Map;

public class CryingIronArmorItem extends ArmorItem {

    public CryingIronArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }

    @Override
    public void onInventoryTick(ItemStack pStack, Level pLevel, Player pPlayer, int pSlotIndex, int pSelectedIndex) {
        if (!pLevel.isClientSide()){
            if(hasFullCryingIronOn(pPlayer)) {

                pPlayer.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 1, false, false, true));
            }
        }
    }

    private boolean hasFullCryingIronOn(Player pPlayer) {
        boolean hasCryingIronHelmet  = pPlayer.getInventory().getArmor(3).getItem() instanceof CryingIronArmorItem;
        boolean hasCryingIronChestplate  = pPlayer.getInventory().getArmor(2).getItem() instanceof CryingIronArmorItem;;
        boolean hasCryingIronLeggings  = pPlayer.getInventory().getArmor(1).getItem() instanceof CryingIronArmorItem;;
        boolean hasCryingIronBoots  = pPlayer.getInventory().getArmor(0).getItem() instanceof CryingIronArmorItem;;
        return hasCryingIronHelmet && hasCryingIronChestplate && hasCryingIronLeggings && hasCryingIronBoots;
    }
}