package com.gusgutsy.afterhalls.handler;

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

        CompoundTag tag = entity.getPersistentData(); // Used to retrieve the tags from potential villager victim

        // Check that the entity can be converted to a zombie now.
        if (entity instanceof Villager villager && tag.getBoolean("rotbrandConvertToZombie")) {
            // Calculate time left
            int timeLeft = tag.getInt("rotbrandConversionTime") - 1;
            tag.putInt("rotbrandConversionTime", timeLeft);

            // When time runs out, transform
            if (timeLeft <= 0 && entity.level() instanceof ServerLevel serverLevel) {
                // set up the zombie
                ZombieVillager zombieVillager = EntityType.ZOMBIE_VILLAGER.create(serverLevel);

                if (zombieVillager != null) {
                    // Emit sound and smoke particles with a random offset from the villager
                    villager.playSound(SoundEvents.ZOMBIE_INFECT);
                    RandomSource random = serverLevel.getRandom();
                    double dx = villager.getX() + (random.nextDouble() - 0.5);
                    double dy = villager.getY() + random.nextDouble() * villager.getBbHeight();
                    double dz = villager.getZ() + (random.nextDouble() - 0.5);
                    serverLevel.sendParticles(ParticleTypes.SMOKE, dx, dy, dz, 20, 0.5, 0.5, 0.5, 0.07);

                    // swap out the villager and zombie entities
                    zombieVillager.moveTo(
                            villager.getX(),
                            villager.getY(),
                            villager.getZ(),
                            villager.getYRot(),
                            villager.getXRot()
                    );
                    zombieVillager.setPose(villager.getPose());
                    zombieVillager.setCustomName(villager.getCustomName());
                    zombieVillager.setCustomNameVisible(villager.isCustomNameVisible());
                    zombieVillager.setVillagerData(villager.getVillagerData());
                    zombieVillager.setBaby((villager.isBaby()));
                    serverLevel.addFreshEntity(zombieVillager);

                    // Clean up
                    villager.discard();
                    tag.remove("rotbrandConversionTime");
                    tag.remove("rotbrandConvertToZombie");
                }
            }
        }
    }
}
