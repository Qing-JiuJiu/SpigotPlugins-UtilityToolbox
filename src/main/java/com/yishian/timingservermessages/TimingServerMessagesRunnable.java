package com.yishian.timingservermessages;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author XinQi
 */
public class TimingServerMessagesRunnable extends BukkitRunnable {
    @Override
    public void run() {
        //广播消息
        Bukkit.getServer().broadcastMessage("cc");
    }
}
