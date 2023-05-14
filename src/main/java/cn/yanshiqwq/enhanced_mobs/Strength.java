package cn.yanshiqwq.enhanced_mobs;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import static cn.yanshiqwq.enhanced_mobs.util.*;

public class Strength {
    public static void setStrength(LivingEntity entity) {
        addGlowingFrame(entity, "strength");
        disableBurnInDay(entity);

        entity.addPotionEffect(
                new PotionEffect(PotionEffectType.REGENERATION, PotionEffect.INFINITE_DURATION, 0)
        );

        if (entity instanceof Zombie zombie) {
            zombie(zombie);
        } else if (entity instanceof AbstractSkeleton skeleton) {
            Random r = new Random();

            int random = r.nextInt(9);
            int skeletonBowWeight = 7;

            if (random < skeletonBowWeight) {
                skeletonBow(skeleton);
            } else {
                skeletonSword(skeleton);
            }
        } else if (entity instanceof Creeper creeper) {
            creeper(creeper);
        } else if (entity instanceof Spider spider) {
            spider(spider);
        }

        resetHealth(entity);
    }

    public static void creeper(Creeper entity) {
        entity.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
        entity.registerAttribute(Attribute.GENERIC_MOVEMENT_SPEED);

        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(32.0f);
        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(0.32f);

        entity.setMaxFuseTicks(24);
        entity.setExplosionRadius(5);
        creeperAttackTickEvent(entity).runTaskTimer(Main.getInstance(), 5, 10);
    }

    public static void skeletonBow(AbstractSkeleton entity) {
        entity.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
        entity.registerAttribute(Attribute.GENERIC_MOVEMENT_SPEED);

        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(42.0f);
        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(0.42f);

        Map<Enchantment, Integer> enchantList = new HashMap<>();
        enchantList.put(Enchantment.ARROW_DAMAGE, 6);
        enchantList.put(Enchantment.ARROW_KNOCKBACK, 1);

        ItemStack bowItem = new ItemStack(Material.BOW, 1);
        bowItem.addUnsafeEnchantments(enchantList);
        entity.getEquipment().setItemInMainHand(bowItem);
        entity.getEquipment().setItemInMainHandDropChance(0.0f);
    }

    public static void skeletonSword(AbstractSkeleton entity) {
        entity.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
        entity.registerAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        entity.registerAttribute(Attribute.GENERIC_ATTACK_DAMAGE);

        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(42.0f);
        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(0.42f);
        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(6.0f);

        ItemStack swordItem = new ItemStack(Material.IRON_SWORD, 1);
        entity.getEquipment().setItemInMainHand(swordItem);
        entity.getEquipment().setItemInMainHandDropChance(0.0f);
    }

    private static void zombie(Zombie entity) {
        entity.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
        entity.registerAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        entity.registerAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        entity.registerAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE);

        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(48.0f);
        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(0.36f);
        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(6.0f);
        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)).setBaseValue(0.15f);
    }

    private static void spider(Spider entity) {
        entity.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
        entity.registerAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        entity.registerAttribute(Attribute.GENERIC_ATTACK_DAMAGE);

        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(48.0f);
        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(0.32f);
        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(6.0f);
    }
    public static BukkitRunnable creeperAttackTickEvent(Creeper creeper) {
        return new BukkitRunnable(){
            @Override
            public void run(){
                LivingEntity target = (LivingEntity) creeper.getTargetEntity(4);
                Random r = new Random();

                if (target != null) {
                    target.damage(3.0f);
                }
            }
        };
    }
}
