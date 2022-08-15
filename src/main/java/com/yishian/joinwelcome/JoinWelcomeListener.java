package com.yishian.joinwelcome;

import com.yishian.Main;
import com.yishian.common.ServerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import java.util.List;

/**
 * @author XinQi
 */
public class JoinWelcomeListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent playerJoinEvent) {
        //获取加入欢迎的信息列表
        List<String> joinServerWelcomeMessageList = ServerUtils.getServerConfig().getConfigurationSection("join-server-welcome").getConfigurationSection("message").getStringList("join-server-welcome-message");

        //发送欢迎内容
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getProvidingPlugin(Main.class), new Runnable() {
            public void run() {
                Player player = playerJoinEvent.getPlayer();
                joinServerWelcomeMessageList.forEach(joinServerWelcomeMessage -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', joinServerWelcomeMessage.replaceAll("%player%", player.getName()))));
            }
        });


    }

}
