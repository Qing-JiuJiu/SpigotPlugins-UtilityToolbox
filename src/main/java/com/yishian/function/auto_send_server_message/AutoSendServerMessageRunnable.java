package com.yishian.function.auto_send_server_message;

import com.yishian.common.CommonEnum;
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
        ConfigurationSection messagesconfigurationSection = PluginUtils.getServerConfig().getConfigurationSection("auto-send-server-messages").getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        List<String> sendMessageList = messagesconfigurationSection.getStringList("send-messages");
        //广播消息
        sendMessageList.forEach(message -> Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', message)));
    }
}
