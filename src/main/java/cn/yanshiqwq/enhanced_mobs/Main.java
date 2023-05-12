package cn.yanshiqwq.enhanced_mobs;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("插件已加载！");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("插件已卸载！");
    }
}
