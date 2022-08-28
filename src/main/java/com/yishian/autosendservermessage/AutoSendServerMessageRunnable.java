package com.yishian.autosendservermessage;

import com.yishian.common.PluginUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

/**
 * @author XinQi
 */
public class AutoSendServerMessageRunnable extends BukkitRunnable {

    @Override
    public void run() {
        ConfigurationSection messagesconfigurationSection = PluginUtils.getServerConfig().getConfigurationSection("auto-send-server-messages").getConfigurationSection("message");
        List<String> sendMessageList = messagesconfigurationSection.getStringList("send-messages");
        //广播消息
        sendMessageList.forEach(message -> Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', message)));
    }
}
