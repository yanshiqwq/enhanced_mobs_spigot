package cn.yanshiqwq.enhanced_mobs.zombie;

import cn.yanshiqwq.enhanced_mobs.util.*;
import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Random;

import static cn.yanshiqwq.enhanced_mobs.util.getWeightedRandomMaterial;
import static cn.yanshiqwq.enhanced_mobs.util.getYState;

public class Equipments implements Listener {
    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent event) {
        if (!(event.getEntity() instanceof Zombie zombie)) {
            return;
        }
        zombie.getEquipment().setItemInMainHand(getRandomMainHandTool(zombie.getLocation().getY(), zombie.getWorld().getEnvironment()));
    }

    public static ItemStack getRandomMainHandTool(double yPos, Environment dimension) {
        YState yState = getYState(yPos);
        HashMap<Material, Integer> stoneTools = new HashMap<>();
        stoneTools.put(Material.STONE_AXE, 2);
        stoneTools.put(Material.STONE_SWORD, 5);
        stoneTools.put(Material.STONE_PICKAXE, 2);

        HashMap<Material, Integer> ironTools = new HashMap<>();
        ironTools.put(Material.IRON_AXE, 2);
        ironTools.put(Material.IRON_SWORD, 4);
        ironTools.put(Material.IRON_PICKAXE, 2);
        ironTools.put(Material.IRON_SHOVEL, 5);

        HashMap<Material, Integer> goldTools = new HashMap<>();
        ironTools.put(Material.GOLDEN_AXE, 3);
        ironTools.put(Material.GOLDEN_SWORD, 5);
        ironTools.put(Material.GOLDEN_PICKAXE, 2);

        HashMap<Material, Integer> diamondTools = new HashMap<>();
        diamondTools.put(Material.DIAMOND_AXE, 2);
        diamondTools.put(Material.DIAMOND_SWORD, 5);
        diamondTools.put(Material.DIAMOND_PICKAXE, 1);

        int stoneWeight = 0;
        int ironWeight = 0;
        int goldWeight = 0;
        int diamondWeight = 0;
        double enchantChance = 0.0f;
        if (dimension != Environment.NETHER) {
            switch (yState) {
                case ABOVE_SEA_LEVEL -> {
                    stoneWeight = 10;
                    ironWeight = 25;
                    goldWeight = 0;
                    diamondWeight = 1;
                    enchantChance = 0.1f;
                }
                case UNDERGROUND -> {
                    stoneWeight = 2;
                    ironWeight = 15;
                    goldWeight = 0;
                    diamondWeight = 1;
                    enchantChance = 0.2f;
                }
                case DEEP_UNDERGROUND -> {
                    stoneWeight = 1;
                    ironWeight = 15;
                    goldWeight = 0;
                    diamondWeight = 2;
                    enchantChance = 0.35f;
                }
            }
        } else {
            stoneWeight = 0;
            ironWeight = 2;
            goldWeight = 30;
            diamondWeight = 1;
            enchantChance = 0.5f;
        }

        int random = new Random().nextInt(stoneWeight + ironWeight + goldWeight + diamondWeight);

        Material toolMaterial = null;

        if (random < stoneWeight) { // 如果随机数在stone的权重范围内
            toolMaterial = getWeightedRandomMaterial(stoneTools);
        } else if (random < stoneWeight + ironWeight) { // 如果随机数在iron的权重范围内
            toolMaterial = getWeightedRandomMaterial(ironTools);
        } else if (random < stoneWeight + ironWeight + goldWeight) { // 如果随机数在gold的权重范围内
            toolMaterial = getWeightedRandomMaterial(goldTools);
        } else { // 如果随机数在diamond的权重范围内
            toolMaterial = getWeightedRandomMaterial(diamondTools);
        }

        ItemStack toolItem = new ItemStack(toolMaterial);
        if (Math.random() < enchantChance) {
            toolItem.enchantWithLevels(16, false, new Random());
        }
        return toolItem;
    }

    public ItemStack getRandomOffHandTool(double yPos, Environment dimension) {
        YState yState = getYState(yPos);
        HashMap<Material, Integer> blocks = new HashMap<>();
        switch (dimension) {
            case NETHER -> {
                blocks.put(Material.NETHERRACK, 20);
                blocks.put(Material.SOUL_SAND, 1);
                blocks.put(Material.SOUL_SOIL, 3);
                blocks.put(Material.BLACKSTONE, 10);
                blocks.put(Material.BASALT, 5);
            }
            case THE_END -> {
                blocks.put(Material.END_STONE, 100);
                blocks.put(Material.END_STONE_BRICKS, 10);
                blocks.put(Material.PURPUR_BLOCK, 1);
            }
            default -> {
                blocks.put(Material.COBBLESTONE, 30);
                blocks.put(Material.DIRT, 10);
                blocks.put(Material.SANDSTONE, 3);
                blocks.put(Material.OAK_PLANKS, 10);
                blocks.put(Material.OAK_LEAVES, 1);
                blocks.put(Material.WHITE_WOOL, 1);
                blocks.put(Material.STONE, 1);
            }
        }

        HashMap<Material, Integer> misc = new HashMap<>();
        misc.put(Material.SHIELD, 10);
        misc.put(Material.TOTEM_OF_UNDYING, 5);
        misc.put(Material.TNT, 5);
        switch (dimension) {
            case NETHER -> {
                misc.put(Material.RED_BED, 2);
                misc.put(Material.SNOWBALL, 1);
                misc.put(Material.ENDER_PEARL, 3);
                misc.put(Material.LAVA_BUCKET, 3);
                misc.put(Material.FLINT_AND_STEEL, 3);
                misc.put(Material.FIRE_CHARGE, 3);
            }
            case THE_END -> {
                misc.put(Material.END_CRYSTAL, 1);
                misc.put(Material.RED_BED, 2);
                misc.put(Material.SNOWBALL, 3);
                misc.put(Material.ENDER_PEARL, 10);
                misc.put(Material.WATER_BUCKET, 3);
                misc.put(Material.LAVA_BUCKET, 2);
                misc.put(Material.FLINT_AND_STEEL, 2);
                misc.put(Material.FIRE_CHARGE, 1);
            }
            default -> {
                misc.put(Material.SNOWBALL, 3);
                misc.put(Material.ENDER_PEARL, 3);
                misc.put(Material.WATER_BUCKET, 3);
                misc.put(Material.LAVA_BUCKET, 2);
                misc.put(Material.FIREWORK_ROCKET, 2);
                misc.put(Material.FLINT_AND_STEEL, 2);
                misc.put(Material.FIRE_CHARGE, 1);
                misc.put(Material.EGG, 1);
            }
        }

        return null;
    }
}
