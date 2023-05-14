package cn.yanshiqwq.enhanced_mobs;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import static cn.yanshiqwq.enhanced_mobs.Enhance.setEnhance;
import static cn.yanshiqwq.enhanced_mobs.Strength.setStrength;

public class Spawn implements Listener {

    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent event) {
        LivingEntity entity = event.getEntity();

        // 限制自然生成 and 刷怪蛋
        CreatureSpawnEvent.SpawnReason reason = event.getSpawnReason();
        if (reason != CreatureSpawnEvent.SpawnReason.NATURAL
                && reason != CreatureSpawnEvent.SpawnReason.SPAWNER_EGG) {
            return;
        }

        // 自然变异概率
        double chance = 0.075;

        // 普通增强概率
        // double enhanceChance = 1 - strengthChance;
        double strengthChance = 0.7;

        if (Math.random() > chance) {
            return;
        }

        if (Math.random() <= strengthChance) {
            setStrength(entity);
        } else {
            setEnhance(entity);
        }
    }
}