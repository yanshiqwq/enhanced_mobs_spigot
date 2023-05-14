package cn.yanshiqwq.enhanced_mobs;


import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

import static cn.yanshiqwq.enhanced_mobs.util.*;

public class Enhance{
    public static void setEnhance(LivingEntity entity) {
        addGlowingFrame(entity, "enhance");
        disableBurnInDay(entity);
        setFireResistance(entity);

        List<PotionEffect> effects = new ArrayList<>();
        effects.add(new PotionEffect(PotionEffectType.REGENERATION, PotionEffect.INFINITE_DURATION, 1));
        effects.add(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, PotionEffect.INFINITE_DURATION, 1));
        effects.add(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, PotionEffect.INFINITE_DURATION, 0));

        entity.addPotionEffects(effects);

        if (entity instanceof Zombie zombie) {
            zombie(zombie);
        } else if (entity instanceof AbstractSkeleton skeleton) {
            skeleton(skeleton);
        } else if (entity instanceof Creeper creeper) {
            creeper(creeper);
        } else if (entity instanceof Spider spider) {
            spider(spider);
        }

        resetHealth(entity);
    }

    public static void creeper(Creeper entity){
        entity.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
        entity.registerAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        entity.registerAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE);

        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(36.0f);
        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(0.36f);
        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)).setBaseValue(0.3f);

        entity.setMaxFuseTicks(45);
        entity.setExplosionRadius(10);
        creeperAttackTickEvent(entity).runTaskTimer(Main.getInstance(), 5, 10);
    }

    public static void skeleton(AbstractSkeleton entity) {
        entity.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
        entity.registerAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        entity.registerAttribute(Attribute.GENERIC_ATTACK_SPEED);

        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(64.0f);
        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(0.45f);
        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_ATTACK_SPEED)).setBaseValue(1.0f);

        Map<Enchantment, Integer> enchantList = new HashMap<>();
        enchantList.put(Enchantment.ARROW_DAMAGE, 13);
        enchantList.put(Enchantment.ARROW_KNOCKBACK, 2);
        enchantList.put(Enchantment.ARROW_FIRE, 1);

        Objects.requireNonNull(entity.getEquipment()).getItemInMainHand().addUnsafeEnchantments(enchantList);
    }

    private static void zombie(Zombie entity) {
        entity.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
        entity.registerAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        entity.registerAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        entity.registerAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE);

        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(72.0f);
        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(0.42f);
        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(13.0f);
        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)).setBaseValue(0.15f);
    }

    private static void spider(Spider entity) {
        entity.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
        entity.registerAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        entity.registerAttribute(Attribute.GENERIC_ATTACK_DAMAGE);

        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(52.0f);
        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(0.36f);
        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(6f);

        spiderPoisonTickEvent(entity).runTaskTimer(Main.getInstance(), 5, 8);
    }

    public static BukkitRunnable spiderPoisonTickEvent(Spider spider) {
        return new BukkitRunnable(){
            @Override
            public void run(){
                LivingEntity target = (LivingEntity) spider.getTargetEntity(6);
                Random r = new Random();

                if (target != null && !target.isInWater()) {
                    target.addPotionEffect(new PotionEffect(PotionEffectType.POISON, r.nextInt(160) + 20, 1, false, false, false));
                }
            }
        };
    }

    public static BukkitRunnable creeperAttackTickEvent(Creeper creeper) {
        return new BukkitRunnable(){
            @Override
            public void run(){
                LivingEntity target = (LivingEntity) creeper.getTargetEntity(4);
                Random r = new Random();

                if (target != null) {
                    target.damage(6.0f);
                }
            }
        };
    }
}