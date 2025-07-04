package com.gusgutsy.afterhalls.item.cryingiron;

import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CryingIronArmorItem extends ArmorItem {

    public CryingIronArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }

    @Override
    public void onInventoryTick(ItemStack pStack, Level pLevel, Player pPlayer, int pSlotIndex, int pSelectedIndex) {
        if (!pLevel.isClientSide()){
            // Activate only after being hit this tick if wearing full Crying iron armor by an undead mob.
            if(hasFullCryingIronOn(pPlayer) && hurtThisTick(pPlayer) && wasHurtByUndead(pPlayer)) {
                // Activates only 50% of the time to instantly repair armor damage and to add a
                // damage boost effect.
                RandomSource random = pLevel.getRandom();
                if (random.nextFloat() >= 0.5f) {
                    pStack.setDamageValue(Math.max(0, pStack.getDamageValue() - 1));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,
                            100, 1, false, false, true));
                }
            }
        }
    }

    private boolean hasFullCryingIronOn(Player pPlayer) {
        boolean hasCryingIronHelmet  = pPlayer.getInventory().getArmor(3).getItem() instanceof CryingIronArmorItem;
        boolean hasCryingIronChestplate  = pPlayer.getInventory().getArmor(2).getItem() instanceof CryingIronArmorItem;
        boolean hasCryingIronLeggings  = pPlayer.getInventory().getArmor(1).getItem() instanceof CryingIronArmorItem;
        boolean hasCryingIronBoots  = pPlayer.getInventory().getArmor(0).getItem() instanceof CryingIronArmorItem;
        return hasCryingIronHelmet && hasCryingIronChestplate && hasCryingIronLeggings && hasCryingIronBoots;
    }

    private boolean hurtThisTick(Player pPlayer){
        return pPlayer.getLastHurtByMobTimestamp() == pPlayer.tickCount;
    }

    private boolean wasHurtByUndead(Player pPlayer){
        LivingEntity attackingMob = pPlayer.getLastHurtByMob();
        if (attackingMob != null){
            return attackingMob.getMobType() == MobType.UNDEAD;
        }
        return false;
    }
}