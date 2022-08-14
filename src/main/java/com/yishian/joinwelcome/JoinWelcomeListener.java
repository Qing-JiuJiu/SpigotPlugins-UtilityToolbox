package com.yishian.joinwelcome;

import com.yishian.common.ServerUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class JoinWelcomeListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent playerJoinEvent) {
        //获取配置文件
        ConfigurationSection configurationSection = ServerUtils.getServerConfig();
        ConfigurationSection joinServerMessage = configurationSection.getConfigurationSection("join-server-welcome").getConfigurationSection("message");
        List<String> joinServerWelcomeMessageList = joinServerMessage.getStringList("join-server-welcome-message");

        Player player = playerJoinEvent.getPlayer();

        joinServerWelcomeMessageList.forEach(joinServerWelcomeMessage -> {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', joinServerWelcomeMessage.replaceAll("%player%", player.getName())));
        });


    }

}
