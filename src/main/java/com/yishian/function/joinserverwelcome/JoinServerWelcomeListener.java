package com.yishian.function.joinserverwelcome;

import com.yishian.Main;
import com.yishian.common.CommonEnum;
import com.yishian.common.CommonUtils;
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

    /**
     * 得到配置文件
     */
   static ConfigurationSection functionConfigurationSection = CommonUtils.ServerConfig.getConfigurationSection("join-server-welcome");
    /**
     * 得到消息列表
     */
   static ConfigurationSection functionMessageConfigurationSection = functionConfigurationSection.getConfigurationSection(CommonEnum.MESSAGE.getCommand());
   /**
    * 得到欢迎内容
    */
   static List<String> messageList = functionMessageConfigurationSection.getStringList("join-server-welcome-message");

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent playerJoinEvent) {
        //发送欢迎内容
        if (!CommonUtils.collectionIsEmpty(messageList)) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getProvidingPlugin(Main.class), () -> {
                Player player = playerJoinEvent.getPlayer();
                //判断是否开启第一次进入服务器欢迎，没开启就直接发送消息列表即可
                if (functionConfigurationSection.getBoolean("first-join-server-welcome-enable")) {
                    //判断玩家是否第一次加入服务器
                    if (player.hasPlayedBefore()) {
                        //后续来到服务器
                        messageList.forEach(joinServerWelcomeMessage -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', joinServerWelcomeMessage.replaceAll("%player%", player.getName()))));
                    } else {
                        //第一次来到服务器
                        functionMessageConfigurationSection.getStringList("first-join-server-welcome-message").forEach(joinServerWelcomeMessage -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', joinServerWelcomeMessage.replaceAll("%player%", player.getName()))));
                    }
                } else {
                    messageList.forEach(joinServerWelcomeMessage -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', joinServerWelcomeMessage.replaceAll("%player%", player.getName()))));
                }
            });
        }
    }
}
