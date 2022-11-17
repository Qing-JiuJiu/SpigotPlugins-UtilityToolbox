package com.yishian.function.nodeathdrop;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
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
    @EventHandler(priority = EventPriority.LOWEST)
    public void noDeathDrop(PlayerDeathEvent playerDeathEvent) {
        //保存库存并取消物品掉落
        playerDeathEvent.getDrops().clear();
        playerDeathEvent.setKeepInventory(true);

        //保存经验并取消经验掉落
        playerDeathEvent.setDroppedExp(0);
        playerDeathEvent.setKeepLevel(true);
    }
}
