package cn.yanshiqwq.enhanced_mobs;

import cn.yanshiqwq.enhanced_mobs.Command.randomItem;
import cn.yanshiqwq.enhanced_mobs.Command.spawnMob;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Objects;

public final class Main extends JavaPlugin {

    public static Main INSTANCE = null;
    public static Scoreboard SCOREBOARD = null;

    @Override
    @SuppressWarnings("deprecation")
    public void onEnable() {
        INSTANCE = this;
        getLogger().info("插件已加载！");

        getServer().getPluginManager().registerEvents(new Spawn(), this);
        Objects.requireNonNull(this.getCommand("spawnmob")).setExecutor(new spawnMob());
        Objects.requireNonNull(this.getCommand("randomitem")).setExecutor(new randomItem());

        SCOREBOARD = getServer().getScoreboardManager().getMainScoreboard();

        try {
            SCOREBOARD.registerNewTeam("strength").setColor(ChatColor.AQUA);
            SCOREBOARD.registerNewTeam("enhance").setColor(ChatColor.LIGHT_PURPLE);
        } catch (Exception ignored){}

    }

    @Override
    public void onDisable() {
        getLogger().info("插件已卸载！");
    }

    public static Main getInstance() {
        return INSTANCE;
    }
}
