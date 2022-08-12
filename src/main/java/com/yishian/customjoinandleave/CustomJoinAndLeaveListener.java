package com.yishian.customjoinandleave;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author XinQi
 */
public class CustomJoinAndLeaveListener implements Listener {

    /**
     * 当玩家加入服务器时触发的方法
     *
     * @param playerJoinEvent 玩家加入服务器监听
     */
    @EventHandler
    public void playerOnJoin(PlayerJoinEvent playerJoinEvent) {
        String displayName = playerJoinEvent.getPlayer().getDisplayName();
        playerJoinEvent.setJoinMessage(displayName + "欢迎加入本服务器");
    }

    /**
     * 当玩家退出服务器时触发的方法
     *
     * @param playerQuitEvent 玩家离开服务器监听
     */
    @EventHandler
    public void playerOnLeave(PlayerQuitEvent playerQuitEvent) {
        String displayName = playerQuitEvent.getPlayer().getDisplayName();
        playerQuitEvent.setQuitMessage(displayName + "已离开本服务器");
    }
}
