package com.yishian.antihighfrequencyredstone;

import com.yishian.common.PluginUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

/**
 * @author XinQi
 */
public class AntiHighFrequencyRedStoneRunnable extends BukkitRunnable {

    /**
     * Key为坐标，Value为出现的次数
     */
    static HashMap<Location, Integer> detectList = new HashMap<>();

    @Override
    public void run() {
        ConfigurationSection functionConfigurationSection = PluginUtils.getServerConfig().getConfigurationSection("anti-high-frequency-red-stone");
        int limit = functionConfigurationSection.getInt("limit");
        //循环获取Value比对出现次数，超过出现次数就将该红石去除
        detectList.forEach((location, frequency) -> {
            if (frequency.compareTo(limit) >= 0) {
                //普通破坏，就跟玩家挖掘一样。
                location.getBlock().breakNaturally();

                //TODO 为完善
                Bukkit.getServer().broadcastMessage(location.getBlockX() + "---" + frequency);
            }
        });
        //判断完后清除所有数据
        detectList.clear();
    }
}
