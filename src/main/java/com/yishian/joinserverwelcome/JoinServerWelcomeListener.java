package com.yishian.joinserverwelcome;

import com.yishian.Main;
import com.yishian.common.ServerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

/**
 * @author XinQi
 */
public class JoinServerWelcomeListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent playerJoinEvent) {
        ConfigurationSection joinServerWelcomeConfigurationSection = ServerUtils.getServerConfig().getConfigurationSection("join-server-welcome");
        ConfigurationSection joinServerWelcomeMessageConfigurationSection = joinServerWelcomeConfigurationSection.getConfigurationSection("message");
        List<String> joinServerWelcomeMessageList = joinServerWelcomeMessageConfigurationSection.getStringList("join-server-welcome-message");
        List<String> firstJoinServerWelcomeMessageList = joinServerWelcomeMessageConfigurationSection.getStringList("first-join-server-welcome-message");
        //发送欢迎内容
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getProvidingPlugin(Main.class), new Runnable() {
            public void run() {
                Player player = playerJoinEvent.getPlayer();
                //判断是否开启第一次进入服务器欢迎，没开启就直接发送消息列表即可
                if (joinServerWelcomeConfigurationSection.getBoolean("first-join-server-welcome-enable")) {
                    if (player.hasPlayedBefore()) {
                        //后续来到服务器
                        joinServerWelcomeMessageList.forEach(joinServerWelcomeMessage -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', joinServerWelcomeMessage.replaceAll("%player%", player.getName()))));
                    } else {
                        //第一次来到服务器
                        firstJoinServerWelcomeMessageList.forEach(joinServerWelcomeMessage -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', joinServerWelcomeMessage.replaceAll("%player%", player.getName()))));
                    }
                } else {
                    joinServerWelcomeMessageList.forEach(joinServerWelcomeMessage -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', joinServerWelcomeMessage.replaceAll("%player%", player.getName()))));
                }
            }
        });
    }
}
