package cn.yanshiqwq.enhanced_mobs;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.Arrays;

public class Data {
    public final static ArrayList<EntityType> zombies = new ArrayList<>(Arrays.asList(EntityType.ZOMBIE, EntityType.ZOMBIE_VILLAGER, EntityType.HUSK, EntityType.DROWNED));
    public final static ArrayList<EntityType> skeletons = new ArrayList<>(Arrays.asList(EntityType.SKELETON, EntityType.STRAY, EntityType.WITHER_SKELETON));
    public final static ArrayList<EntityType> spiders = new ArrayList<>(Arrays.asList(EntityType.SPIDER, EntityType.CAVE_SPIDER));
}
