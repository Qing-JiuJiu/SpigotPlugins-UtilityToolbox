package com.yishian.function.customjoinandleave;

import com.yishian.common.CommonEnum;
import com.yishian.common.CommonUtils;
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
     * 得到该功能的配置文件坐标
     */
    static ConfigurationSection functionConfigurationSection = CommonUtils.ServerConfig.getConfigurationSection("join-and-leave-server-message");
    /**
     * 得到消息列表
     */
    static ConfigurationSection messageConfigurationSection = functionConfigurationSection.getConfigurationSection(CommonEnum.MESSAGE.getCommand());

    /**
     * 当玩家加入服务器时触发的方法
     *
     * @param playerJoinEvent 玩家加入服务器监听
     */
    @EventHandler
    public void playerOnJoin(PlayerJoinEvent playerJoinEvent) {
        //得到玩家
        Player player = playerJoinEvent.getPlayer();
        String playerDisplayName = player.getDisplayName();
        //判断是否开启第一次加入服务器功能
        if (functionConfigurationSection.getBoolean("first-join-server-message-enable")) {
            //判断玩家是否第一次加入服务器
            if (player.hasPlayedBefore()) {
                //后续来到服务器
                playerJoinEvent.setJoinMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + messageConfigurationSection.getString("player-join-server-message").replaceAll("%player%", playerDisplayName)));
            } else {
                //第一次加入服务器
                playerJoinEvent.setJoinMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + messageConfigurationSection.getString("player-first-join-server-message").replaceAll("%player%", playerDisplayName)));
            }
        } else {
            playerJoinEvent.setJoinMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + messageConfigurationSection.getString("player-join-server-message").replaceAll("%player%", playerDisplayName)));
        }
    }

    /**
     * 当玩家退出服务器时触发的方法
     *
     * @param playerQuitEvent 玩家离开服务器监听
     */
    @EventHandler
    public void playerOnLeave(PlayerQuitEvent playerQuitEvent) {
        //发送离开服务器消息
        playerQuitEvent.setQuitMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + messageConfigurationSection.getString("player-leave-server-message").replaceAll("%player%", playerQuitEvent.getPlayer().getDisplayName())));
    }
}
