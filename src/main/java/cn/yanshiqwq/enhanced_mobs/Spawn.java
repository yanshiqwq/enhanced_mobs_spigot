package cn.yanshiqwq.enhanced_mobs;

import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class Spawn implements Listener {

    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent event) {

        if (event.getEntity().hasAI()) {

        }
    }
}