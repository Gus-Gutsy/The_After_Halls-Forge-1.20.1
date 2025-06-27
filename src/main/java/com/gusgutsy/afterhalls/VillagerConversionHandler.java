package com.gusgutsy.afterhalls;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class VillagerConversionHandler {

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();

        CompoundTag tag = entity.getPersistentData();
        if (tag.getBoolean("rotbrandConvertToZombie")) {

            int timeLeft = tag.getInt("rotbrandConversionTime") - 1;
            tag.putInt("rotbrandConversionTime", timeLeft);

            // When time runs out: transform
            if (timeLeft <= 0 && entity.level() instanceof ServerLevel serverLevel && entity instanceof Villager villager) {

                ZombieVillager zombieVillager = EntityType.ZOMBIE_VILLAGER.create(serverLevel);

                RandomSource random = serverLevel.random;
                if (zombieVillager != null) {
                    villager.playSound(SoundEvents.ZOMBIE_INFECT);

                    // Emit smoke particles

                    double dx = villager.getX() + (random.nextDouble() - 0.5);
                    double dy = villager.getY() + random.nextDouble() * villager.getBbHeight();
                    double dz = villager.getZ() + (random.nextDouble() - 0.5);
                    serverLevel.sendParticles(ParticleTypes.SMOKE, dx, dy, dz, 20, 0.5, 0.5, 0.5, 0.07);


                    zombieVillager.moveTo(villager.blockPosition(), villager.getYRot(), villager.getXRot());
                    zombieVillager.setCustomName(villager.getCustomName());
                    zombieVillager.setCustomNameVisible(villager.isCustomNameVisible());
                    villager.discard();
                    serverLevel.addFreshEntity(zombieVillager);

                    tag.remove("rotbrandConversionTime");
                    tag.remove("rotbrandConvertToZombie");
                }
            }
        }
    }
}
