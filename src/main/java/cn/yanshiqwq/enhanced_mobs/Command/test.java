package cn.yanshiqwq.enhanced_mobs.Command;

import cn.yanshiqwq.enhanced_mobs.Enhance;
import cn.yanshiqwq.enhanced_mobs.Main;
import cn.yanshiqwq.enhanced_mobs.Strength;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

public class test implements CommandExecutor {
    private final Main plugin;
    public test(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            LivingEntity entity;
            if(args.length == 1){
                switch(args[0]){
                    case "strength_zombie":
                        entity = (LivingEntity) player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
                        Strength.setStrength(entity);
                        break;
                    case "enhance_zombie":
                        entity = (LivingEntity) player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
                        Enhance.setEnhance(entity);
                        break;
                    case "strength_skeleton":
                        entity = (LivingEntity) player.getWorld().spawnEntity(player.getLocation(), EntityType.SKELETON);
                        Strength.setStrength(entity);
                        break;
                    case "enhance_skeleton":
                        entity = (LivingEntity) player.getWorld().spawnEntity(player.getLocation(), EntityType.SKELETON);
                        Enhance.setEnhance(entity);
                        break;
                }
            }
        } else {
            sender.sendMessage("You must be a player!");
            return false;
        }
        return false;
    }
}
