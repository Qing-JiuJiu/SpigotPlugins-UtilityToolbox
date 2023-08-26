package com.yishian.function.autosendservermessage;

import com.yishian.common.CommonUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author XinQi
 */
public class AutoSendServerMessageRunnable extends BukkitRunnable {

    @Override
    public void run() {
        //广播消息
        CommonUtil.objectToList(AutoSendServerMessageConfigEnum.SEND_MESSAGES.getMsg()).forEach(message -> Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', message.toString())));
    }
}
