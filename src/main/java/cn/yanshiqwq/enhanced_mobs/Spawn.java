package cn.yanshiqwq.enhanced_mobs;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.Random;

import static cn.yanshiqwq.enhanced_mobs.Enhance.setEnhance;
import static cn.yanshiqwq.enhanced_mobs.Strength.setStrength;

public class Spawn implements Listener {

    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.hasAI()) {
            int noneWeight = 17;
            int strengthWeight = 2;
            int enhanceWeight = 1;
            Random r = new Random();
            int random = r.nextInt(noneWeight + strengthWeight + enhanceWeight - 1);
            if (random >= noneWeight - 1) {
                if (random < noneWeight + strengthWeight - 1) {
                    setStrength(entity);
                } else {
                    setEnhance(entity);
                }
            }
        }
    }
}