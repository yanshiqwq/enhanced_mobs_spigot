package cn.yanshiqwq.enhanced_mobs;

import org.bukkit.Effect;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Arrays;

public class Data {
    public final static ArrayList<EntityType> zombies = new ArrayList<>(Arrays.asList(EntityType.ZOMBIE, EntityType.ZOMBIE_VILLAGER, EntityType.HUSK, EntityType.DROWNED));
    public static void setEnhance(LivingEntity entity) {
        entity.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, PotionEffect.INFINITE_DURATION, 1));
        entity.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, PotionEffect.INFINITE_DURATION, 0));
        if (zombies.contains(entity.getType())) {
            Enhance.zombie(entity);
        } else if (entity.getType() == EntityType.SKELETON) {
            Enhance.skeleton(entity);
        } else if (entity.getType() == EntityType.CREEPER) {
            Enhance.creeper((Creeper) entity);
        }
    }
    public static class Strength{
        public static void creeper(Creeper entity){
            entity.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
            entity.registerAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
            entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(32.0f);
            entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.2f);
            entity.setMaxFuseTicks(24);
            entity.setExplosionRadius(6);
        }

        public static void skeleton_bow(LivingEntity entity) {
            entity.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
            entity.registerAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
            entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(72.0f);
            entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.32f);
            entity.getActiveItem().addEnchantment(Enchantment.ARROW_DAMAGE, 6);
            entity.getActiveItem().addEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
            entity.

        }

        private static void zombie(LivingEntity entity) {
            entity.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
            entity.registerAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
            entity.registerAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
            entity.registerAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
            entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(108.0f);
            entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.32f);
            entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(13f);
            entity.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(0.15f);
        }

        private static void spider(LivingEntity entity) {
            entity.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
            entity.registerAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
            entity.registerAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
            entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(64.0f);
            entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.28f);
            entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(9f);
        }
    }
    public static class Enhance{
        public static void creeper(Creeper entity){
            entity.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
            entity.registerAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
            entity.registerAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
            entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(42.0f);
            entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.24f);
            entity.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(0.3f);
            entity.setMaxFuseTicks(45);
            entity.setExplosionRadius(8);
        }

        public static void skeleton(LivingEntity entity) {
            entity.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
            entity.registerAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
            entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(72.0f);
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
            entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(108.0f);
            entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.32f);
            entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(13f);
            entity.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(0.15f);
        }

        private static void spider(LivingEntity entity) {
            entity.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
            entity.registerAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
            entity.registerAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
            entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(64.0f);
            entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.28f);
            entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(9f);
        }
    }
}
