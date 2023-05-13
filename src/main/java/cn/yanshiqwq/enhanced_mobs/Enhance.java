package cn.yanshiqwq.enhanced_mobs;


import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.joml.Random;

import static cn.yanshiqwq.enhanced_mobs.Data.zombies;
import static org.bukkit.Bukkit.getServer;

public class Enhance{
    public static void setEnhance(LivingEntity entity) {
        entity.setGlowing(true);
        if (entity instanceof Zombie zombie) {
            zombie.setShouldBurnInDay(false);
        } else if (entity instanceof Phantom phantom){
            phantom.setShouldBurnInDay(false);
        } else if (entity instanceof AbstractSkeleton abstractSkeleton){
            abstractSkeleton.setShouldBurnInDay(false);
        }
        getServer().getScoreboardManager().getMainScoreboard().getTeam("enhance").addEntity(entity);
        entity.setFireTicks(-2147483647);
        entity.setVisualFire(false);
        entity.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, PotionEffect.INFINITE_DURATION, 1));
        entity.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, PotionEffect.INFINITE_DURATION, 1));
        entity.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, PotionEffect.INFINITE_DURATION, 0));
        if (zombies.contains(entity.getType())) {
            Enhance.zombie(entity);
        } else if (entity.getType() == EntityType.SKELETON) {
            Enhance.skeleton(entity);
        } else if (entity.getType() == EntityType.CREEPER) {
            Enhance.creeper((Creeper) entity);
        } else if (entity.getType() == EntityType.SPIDER) {
            Enhance.spider(entity);
        }
    }
    public static void creeper(Creeper entity){
        entity.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
        entity.registerAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        entity.registerAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(36.0f);
        entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.24f);
        entity.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(0.3f);
        entity.setMaxFuseTicks(45);
        entity.setExplosionRadius(8);
    }

    public static void skeleton(LivingEntity entity) {
        entity.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
        entity.registerAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(48.0f);
        entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.32f);
        entity.getActiveItem().addEnchantment(Enchantment.ARROW_DAMAGE, 13);
        entity.getActiveItem().addEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
        entity.getActiveItem().addEnchantment(Enchantment.ARROW_FIRE, 1);

    }

    private static void zombie(LivingEntity entity) {
        entity.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
        entity.registerAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        entity.registerAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        entity.registerAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(72.0f);
        entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.32f);
        entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(13.0f);
        entity.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(0.15f);
    }

    private static void spider(LivingEntity entity) {
        entity.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
        entity.registerAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        entity.registerAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(52.0f);
        entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.28f);
        entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(9.0f);
        spiderPoisonTickEvent(entity).runTaskTimer(Main.getInstance(), 5, 20);
    }

    public static BukkitRunnable spiderPoisonTickEvent(LivingEntity spiderEntity) {
        return new BukkitRunnable(){
            @Override
            public void run(){
                LivingEntity target = (LivingEntity) spiderEntity.getTargetEntity(4);
                Random r = new Random();
                if (target != null && r.nextInt(1) == 0 && !target.isInWater()) {
                    target.addPotionEffect(new PotionEffect(PotionEffectType.POISON, r.nextInt(80) + 20, 1, false, false, false));
                }
            }
        };
    }

    public static BukkitRunnable creeperAttackTickEvent(Creeper creeperEntity) {
        return new BukkitRunnable(){
            @Override
            public void run(){
                LivingEntity target = (LivingEntity) creeperEntity.getTargetEntity(4);
                Random r = new Random();
                if (target != null && r.nextInt(1) == 0) {
                    target.damage(7.0f);
                }
            }
        };
    }
}