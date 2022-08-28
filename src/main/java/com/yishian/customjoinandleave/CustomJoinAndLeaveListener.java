package com.yishian.customjoinandleave;

import com.yishian.common.PluginUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
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
        //得到配置文件
        ConfigurationSection configurationSection = PluginUtils.getServerConfig();
        //得到插件名称前缀
        String messagePrefix = configurationSection.getConfigurationSection("plugin-message").getString("message-prefix");
        //得到该功能的配置文件坐标
        ConfigurationSection functionConfigurationSection = configurationSection.getConfigurationSection("join-and-leave-server-message");
        //得到消息列表
        ConfigurationSection messageConfigurationSection = functionConfigurationSection.getConfigurationSection("message");
        //得到玩家
        Player player = playerJoinEvent.getPlayer();
        String playerDisplayName = player.getDisplayName();
        //判断是否开启第一次加入服务器功能
        if (functionConfigurationSection.getBoolean("first-join-server-message-enable")) {
            if (player.hasPlayedBefore()) {
                //后续来到服务器
                playerJoinEvent.setJoinMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + messageConfigurationSection.getString("player-join-server-message").replaceAll("%player%", playerDisplayName)));
            } else {
                //第一次加入服务器
                playerJoinEvent.setJoinMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + messageConfigurationSection.getString("player-first-join-server-message").replaceAll("%player%", playerDisplayName)));
            }
        } else {
            playerJoinEvent.setJoinMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + messageConfigurationSection.getString("player-join-server-message").replaceAll("%player%", playerDisplayName)));
        }
    }

    /**
     * 当玩家退出服务器时触发的方法
     *
     * @param playerQuitEvent 玩家离开服务器监听
     */
    @EventHandler
    public void playerOnLeave(PlayerQuitEvent playerQuitEvent) {
        //得到配置文件
        ConfigurationSection configurationSection = PluginUtils.getServerConfig();
        //得到插件名称前缀
        String messagePrefix = configurationSection.getConfigurationSection("plugin-message").getString("message-prefix");
        //得到该功能的配置文件坐标
        ConfigurationSection joinAndLeaveServerConfigurationSection = configurationSection.getConfigurationSection("join-and-leave-server-message");
        //得到消息列表
        ConfigurationSection customJoinAndLeaveMessage = joinAndLeaveServerConfigurationSection.getConfigurationSection("message");
        //发送离开服务器消息
        playerQuitEvent.setQuitMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + customJoinAndLeaveMessage.getString("player-leave-server-message").replaceAll("%player%", playerQuitEvent.getPlayer().getDisplayName())));
    }
}
