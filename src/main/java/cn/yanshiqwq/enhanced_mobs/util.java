package cn.yanshiqwq.enhanced_mobs;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.AbstractSkeleton;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Zombie;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class util {
    public static void resetHealth(LivingEntity entity) {
        entity.setHealth(Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue());
    }// qwq

    public static void addGlowingFrame(LivingEntity entity, String teamName) {
        Objects.requireNonNull(Main.SCOREBOARD.getTeam(teamName)).addEntity(entity);
        entity.setGlowing(true);
    }

    public static void disableBurnInDay(LivingEntity entity) {
        if (entity instanceof Zombie zombie) {
            zombie.setShouldBurnInDay(false);
        } else if (entity instanceof Phantom phantom){
            phantom.setShouldBurnInDay(false);
        } else if (entity instanceof AbstractSkeleton abstractSkeleton){
            abstractSkeleton.setShouldBurnInDay(false);
        }
    }

    public static void setFireResistance(LivingEntity entity) {
        // Jue boom fire
        entity.setFireTicks(-2147483647);
        entity.setVisualFire(false);
    }

    public enum YState{
        ABOVE_SEA_LEVEL, UNDERGROUND, DEEP_UNDERGROUND
    }

    public static YState getYState(double yPos){
        if (yPos > 63.0f) {
            return YState.ABOVE_SEA_LEVEL;
        } else if (yPos > 0.0f) {
            return YState.UNDERGROUND;
        } else {
            return YState.DEEP_UNDERGROUND;
        }
    }

    public static Material getWeightedRandomMaterial(HashMap<Material, Integer> materialMap) {
        int completeWeight = 0;
        for (int weight : materialMap.values()) {
            completeWeight += weight;
        }

        double random = Math.random() * completeWeight;

        for (Map.Entry<Material, Integer> entry : materialMap.entrySet()) {
            random -= entry.getValue();
            if (random <= 0.0) {
                return entry.getKey();
            }
        }

        // This should never happen
        return Material.AIR;
    }
}
