package com.yishian.customjoinandleave;

import com.yishian.common.ServerUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author XinQi
 */
public class CustomJoinAndLeaveListener implements Listener {

    ConfigurationSection configurationSection = ServerUtils.getServerConfig();
    String messagePrefix = configurationSection.getString("message-prefix");
    ConfigurationSection customJoinAndLeaveMessage = configurationSection.getConfigurationSection("join-and-leave-server-message").getConfigurationSection("message");


    /**
     * 当玩家加入服务器时触发的方法
     *
     * @param playerJoinEvent 玩家加入服务器监听
     */
    @EventHandler
    public void playerOnJoin(PlayerJoinEvent playerJoinEvent) {
        playerJoinEvent.setJoinMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + customJoinAndLeaveMessage.getString("player-join-server-message").replaceAll("%player%", playerJoinEvent.getPlayer().getDisplayName())));
    }

    /**
     * 当玩家退出服务器时触发的方法
     *
     * @param playerQuitEvent 玩家离开服务器监听
     */
    @EventHandler
    public void playerOnLeave(PlayerQuitEvent playerQuitEvent) {
        playerQuitEvent.setQuitMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + customJoinAndLeaveMessage.getString("player-leave-server-message").replaceAll("%player%", playerQuitEvent.getPlayer().getDisplayName())));
    }
}
