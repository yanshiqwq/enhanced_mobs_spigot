package cn.yanshiqwq.enhanced_mobs;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

import static cn.yanshiqwq.enhanced_mobs.Data.*;
import static org.bukkit.Bukkit.getServer;

public class Strength{
    public static void setStrength(LivingEntity entity) {
        entity.setGlowing(true);
        if (entity instanceof Zombie zombie) {
            zombie.setShouldBurnInDay(false);
        } else if (entity instanceof Phantom phantom){
            phantom.setShouldBurnInDay(false);
        } else if (entity instanceof AbstractSkeleton abstractSkeleton){
            abstractSkeleton.setShouldBurnInDay(false);
        }
        getServer().getScoreboardManager().getMainScoreboard().getTeam("strength").addEntity(entity);
        entity.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, PotionEffect.INFINITE_DURATION, 0));
        if (zombies.contains(entity.getType())) {
            zombie(entity);
        } else if (skeletons.contains(entity.getType())) {
            Random r = new Random();
            int random = r.nextInt(9);
            int skeletonBowWeight = 7;
            if (random < skeletonBowWeight) {
                skeleton_bow(entity);
            } else {
                skeleton_sword(entity);
            }
        } else if (entity.getType() == EntityType.CREEPER) {
            creeper((Creeper) entity);
        } else if (spiders.contains(entity.getType())) {
            spider(entity);
        }
    }
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
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(42.0f);
        entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.28f);
        entity.getActiveItem().addEnchantment(Enchantment.ARROW_DAMAGE, 6);
        entity.getActiveItem().addEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
        entity.getEquipment().setItemInMainHandDropChance(0.0f);
    }

    public static void skeleton_sword(LivingEntity entity) {
        entity.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
        entity.registerAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        entity.registerAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(42.0f);
        entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.32f);
        entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(6.0f);
        ItemStack swordItem = new ItemStack(Material.IRON_SWORD, 1);
        entity.getEquipment().setItemInMainHand(swordItem);
        entity.getEquipment().setItemInMainHandDropChance(0.0f);
    }

    private static void zombie(LivingEntity entity) {
        entity.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
        entity.registerAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        entity.registerAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        entity.registerAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(48.0f);
        entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.24f);
        entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(6.0f);
        entity.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(0.15f);
    }

    private static void spider(LivingEntity entity) {
        entity.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
        entity.registerAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        entity.registerAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(48.0f);
        entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.24f);
        entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(6.0f);
    }
}
