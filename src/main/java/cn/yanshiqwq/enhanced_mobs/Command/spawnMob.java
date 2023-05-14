package cn.yanshiqwq.enhanced_mobs.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

import java.util.Objects;

import static cn.yanshiqwq.enhanced_mobs.Enhance.setEnhance;
import static cn.yanshiqwq.enhanced_mobs.Strength.setStrength;

@SuppressWarnings("NullableProblems")
public class spawnMob implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            LivingEntity entity;
            EntityType type;

            if (args.length == 2) {
                switch (args[1]) {
                    case "zombie" -> type = EntityType.ZOMBIE;
                    case "skeleton" -> type = EntityType.SKELETON;
                    case "creeper" -> type = EntityType.CREEPER;
                    case "spider" -> type = EntityType.SPIDER;
                    default -> {
                        return false;
                    }
                }

                entity = (LivingEntity) player.getWorld().spawnEntity(player.getLocation(), type);

                if (Objects.equals(args[0], "strength")) {
                    setStrength(entity);
                } else if (Objects.equals(args[0], "enhance")){
                    setEnhance(entity);
                } else {
                    entity.remove();
                    return false;
                }

                return true;
            }
        } else {
            sender.sendMessage("You must be a player!");
            return false;
        }

        return false;
    }
}
