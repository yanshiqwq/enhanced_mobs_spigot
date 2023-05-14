package cn.yanshiqwq.enhanced_mobs.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import static cn.yanshiqwq.enhanced_mobs.util.getYState;
import static cn.yanshiqwq.enhanced_mobs.zombie.Equipments.getRandomMainHandTool;

@SuppressWarnings("NullableProblems")
public class randomItem implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            LivingEntity entity;
            EntityType type;

            if (args.length == 1) {
                switch (args[0]){
                    case "mainhand" -> {
                        player.setItemInHand(getRandomMainHandTool(player.getLocation().getY(), player.getWorld().getEnvironment()));
                    }
                    case "offhand" -> {
                    }
                }
            }
        } else {
            sender.sendMessage("You must be a player!");
            return false;
        }

        return false;
    }
}
