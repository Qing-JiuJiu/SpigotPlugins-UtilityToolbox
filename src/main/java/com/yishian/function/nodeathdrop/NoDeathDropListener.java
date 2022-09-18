package com.yishian.function.nodeathdrop;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * @author XinQi
 */
public class NoDeathDropListener implements Listener {

    /**
     * 无死亡掉落
     * @param playerDeathEvent 即将死亡的玩家
     */
    @EventHandler
    public void noDeathDrop(PlayerDeathEvent playerDeathEvent) {
        playerDeathEvent.setKeepInventory(true);
        playerDeathEvent.setKeepLevel(true);
    }
}
