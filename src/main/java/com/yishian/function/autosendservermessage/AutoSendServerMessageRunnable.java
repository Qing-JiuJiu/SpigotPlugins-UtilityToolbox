package com.yishian.function.autosendservermessage;

import com.yishian.common.CommonEnum;
import com.yishian.common.CommonUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.List;

/**
 * @author XinQi
 */
public class AutoSendServerMessageRunnable extends BukkitRunnable {

    /**
     * 得到配置文件相关信息
     */
    static ConfigurationSection messagesconfigurationSection = CommonUtils.ServerConfig.getConfigurationSection("auto-send-server-messages").getConfigurationSection(CommonEnum.MESSAGE.getCommand());
    /**
     * 得到需要发送的消息列表
     */
    static List<String> sendMessageList = messagesconfigurationSection.getStringList("send-messages");

    @Override
    public void run() {
        //广播消息
        sendMessageList.forEach(message -> Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', message)));
    }
}
