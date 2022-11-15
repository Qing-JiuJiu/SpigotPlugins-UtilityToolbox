package com.yishian.function.autorespawn;

import com.yishian.Main;

import org.bukkit.Bukkit;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * @author XinQi
 */
public class AutoRespawnListener implements Listener {

    /**
     * 玩家死亡后立即重生
     * @param playerDeathEvent 即将死亡的玩家
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void autoRespawn(PlayerDeathEvent playerDeathEvent) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getProvidingPlugin(Main.class), () -> playerDeathEvent.getEntity().spigot().respawn());
    }
}
