package com.yishian.function.joinserverwelcome;

import com.yishian.Main;
import com.yishian.common.CommonUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @author XinQi
 */
public class JoinServerWelcomeListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent playerJoinEvent) {
            //发送欢迎内容
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getProvidingPlugin(Main.class), () -> {
                Player player = playerJoinEvent.getPlayer();
                //判断是否开启第一次进入服务器欢迎，没开启就直接发送消息列表即可
                if ((Boolean) JoinServerWelcomeConfigEnum.FIRST_JOIN_SERVER_WELCOME_ENABLE.getMsg()) {
                    //判断玩家是否第一次加入服务器
                    if (player.hasPlayedBefore()) {
                        //后续来到服务器
                        CommonUtils.objectToList(JoinServerWelcomeConfigEnum.JOIN_SERVER_WELCOME_MESSAGE.getMsg()).forEach(joinServerWelcomeMessage -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', joinServerWelcomeMessage.toString().replaceAll("%player%", player.getName()))));
                    } else {
                        //第一次来到服务器
                        CommonUtils.objectToList(JoinServerWelcomeConfigEnum.FIRST_JOIN_SERVER_WELCOME_MESSAGE.getMsg()).forEach(joinServerWelcomeMessage -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', joinServerWelcomeMessage.toString().replaceAll("%player%", player.getName()))));
                    }
                } else {
                    CommonUtils.objectToList(JoinServerWelcomeConfigEnum.JOIN_SERVER_WELCOME_MESSAGE.getMsg()).forEach(joinServerWelcomeMessage -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', joinServerWelcomeMessage.toString().replaceAll("%player%", player.getName()))));
                }
            });
        }

}
