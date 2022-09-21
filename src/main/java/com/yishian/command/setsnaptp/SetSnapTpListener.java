package com.yishian.command.setsnaptp;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author XinQi
 */
public class SetSnapTpListener implements Listener {

    /**
     * 当玩家退出服务器时删除临时传送点
     *
     * @param playerQuitEvent 玩家离开服务器监听
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void playerOnLeave(PlayerQuitEvent playerQuitEvent) {
        SetSnapTpCommand.transfeRecordMap.remove(playerQuitEvent.getPlayer());
    }
}
