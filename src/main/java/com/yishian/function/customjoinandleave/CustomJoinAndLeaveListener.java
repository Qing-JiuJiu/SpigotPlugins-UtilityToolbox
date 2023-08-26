package com.yishian.function.customjoinandleave;

import com.yishian.common.CommonMessageEnum;
import org.bukkit.ChatColor;
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
        //得到玩家
        Player player = playerJoinEvent.getPlayer();
        String playerDisplayName = player.getDisplayName();
        //判断是否开启第一次加入服务器功能
        if ((Boolean) CustomJoinAndLeaveConfigEnum.FIRST_JOIN_SERVER_MESSAGE_ENABLE.getMsg()) {
            //判断玩家是否第一次加入服务器
            if (player.hasPlayedBefore()) {
                //后续来到服务器
                playerJoinEvent.setJoinMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + CustomJoinAndLeaveConfigEnum.PLAYER_JOIN_SERVER_MESSAGE.getMsg()).replaceAll("%player%", playerDisplayName));
            } else {
                //第一次加入服务器
                playerJoinEvent.setJoinMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + CustomJoinAndLeaveConfigEnum.PLAYER_FIRST_JOIN_SERVER_MESSAGE.getMsg()).replaceAll("%player%", playerDisplayName));
            }
        } else {
            playerJoinEvent.setJoinMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + CustomJoinAndLeaveConfigEnum.PLAYER_JOIN_SERVER_MESSAGE.getMsg()).replaceAll("%player%", playerDisplayName));
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
        playerQuitEvent.setQuitMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + CustomJoinAndLeaveConfigEnum.PLAYER_LEAVE_SERVER_MESSAGE.getMsg()).replaceAll("%player%", playerQuitEvent.getPlayer().getDisplayName()));
    }
}
