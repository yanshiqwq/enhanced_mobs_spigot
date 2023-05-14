package cn.yanshiqwq.enhanced_mobs.zombie;

import cn.yanshiqwq.enhanced_mobs.Breakable;
import org.bukkit.Location;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Objects;

public class ZombieAI {
    private static final ArrayList<Breakable> goldenPickaxeBreakable = new ArrayList<>() {{
        add(new Breakable(Tag.SCULK_REPLACEABLE));
        add(new Breakable(Tag.SNOW));
        // how to convert this jb - new Breakable(Material.GRAVEL)
        // - but i think tag.sand contains it ** can you test in game? use F3
        add(new Breakable(Material.COBBLESTONE));

        add(new Breakable(Tag.WART_BLOCKS));
        add(new Breakable(Tag.WARPED_STEMS));
    }};
    private static final Integer goldenPickaxeBreakTick = 10;
    private static final ArrayList<Breakable> stonePickaxeBreakable = new ArrayList<>() {{
        addAll(goldenPickaxeBreakable);
        add(new Breakable(Material.COAL_ORE));
        add(new Breakable(Material.IRON_ORE));
        add(new Breakable(Material.COPPER_ORE));
        add(new Breakable(Material.LAPIS_ORE));

        // 别急
    }};
    private static final Integer stonePickaxeBreakTick = 80;
    private static final ArrayList<Breakable> ironPickaxeBreakable = new ArrayList<>(){{
        addAll(stonePickaxeBreakable);
        add(new Breakable(Material.DEEPSLATE_COAL_ORE));
        add(new Breakable(Material.DEEPSLATE_COPPER_ORE));
        add(new Breakable(Material.DEEPSLATE_DIAMOND_ORE));
        add(new Breakable(Material.DEEPSLATE_IRON_ORE));
        add(new Breakable(Material.DEEPSLATE_EMERALD_ORE));
        add(new Breakable(Material.DEEPSLATE_GOLD_ORE));
        add(new Breakable(Material.DEEPSLATE_LAPIS_ORE));
        add(new Breakable(Material.DEEPSLATE_REDSTONE_ORE));
        add(new Breakable(Material.DEEPSLATE));
        add(new Breakable(Material.COBBLED_DEEPSLATE));
    }};
    private static final Integer ironPickaxeBreakTick = 60;
    private static final ArrayList<Breakable> diamondPickaxeBreakable = new ArrayList<>(){{
        addAll(ironPickaxeBreakable);
        add(new Breakable(Material.OBSIDIAN));
        add(new Breakable(Material.CRYING_OBSIDIAN));
    }};
    private static final Integer diamondPickaxeBreakTick = 20;

    // 写点逆天东西
    public static void useMiscTool(LivingEntity entity) {
        switch (Objects.requireNonNull(entity.getEquipment()).getItemInOffHand().getType()){
            case RED_BED:
                if(!(entity.getWorld().isBedWorks() || )){
                    return;
                }
        }
    }
    public static void breakBlock(LivingEntity entity) {
        Location entityLocation = entity.getLocation();
        Block block = entity.getWorld().getBlockAt(entityLocation).getRelative(entity.getFacing());

        // 如果方块能直接通过 爬
        if (block.getType() == Material.AIR || block.isPassable()) {
            return;
        }

        // 如果小于等于一格 且不是牛逼方块 炸！
        // 所有shovel -> dirt类
        // stone_pickaxe -> stone类 dirt类
        // iron_pickaxe -> stone_pickaxe + ore类
        // diamond_pickaxe -> iron_pickaxe + obsidian
        if (block.getLocation().distance(entityLocation) > 1.0) {
            return;
        }



    }
    public static void placeBlock(LivingEntity entity) {}
}
