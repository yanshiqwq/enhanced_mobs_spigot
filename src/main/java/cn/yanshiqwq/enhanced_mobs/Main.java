package cn.yanshiqwq.enhanced_mobs;

import cn.yanshiqwq.enhanced_mobs.Command.test;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

public final class Main extends JavaPlugin {

    public static Main INSTANCE = null;

    @Override
    public void onEnable() {
        INSTANCE = this;
        getLogger().info("插件已加载！");
        this.getCommand("test").setExecutor(new test(this));
        Scoreboard sc = getServer().getScoreboardManager().getMainScoreboard();
        sc.registerNewTeam("strength").setColor(ChatColor.AQUA);
        sc.registerNewTeam("enhance").setColor(ChatColor.LIGHT_PURPLE);
    }

    @Override
    public void onDisable() {
        getLogger().info("插件已卸载！");
    }

    public static Main getInstance() {
        return INSTANCE;
    }
}
