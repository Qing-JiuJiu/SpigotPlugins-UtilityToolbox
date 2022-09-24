package com.yishian.function.anti_high_frequency_red_stone;

import com.yishian.common.CommonEnum;
import com.yishian.common.PluginUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

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
        //得到配置文件相关信息
        FileConfiguration serverConfig = PluginUtils.getServerConfig();
        ConfigurationSection functionConfiguration = serverConfig.getConfigurationSection("anti-high-frequency-red-stone");
        int limit = functionConfiguration.getInt("limit");

        //得到消息前缀和内容
        String messagePrefix = serverConfig.getConfigurationSection(CommonEnum.PLUGIN_MESSAGE.getCommand()).getString(CommonEnum.MESSAGE_PREFIX.getCommand());
        String destroyMessage = functionConfiguration.getConfigurationSection(CommonEnum.MESSAGE.getCommand()).getString("destroy-message");

        //判断是否要广播消息
        if (functionConfiguration.getBoolean(CommonEnum.IS_BROADCAST_MESSAGE.getCommand())) {
            //循环获取Value比对出现次数，超过设置出现次数就将该红石去除
            detectList.forEach((location, frequency) -> {
                if (frequency.compareTo(limit) >= 0) {
                    //普通破坏，就跟玩家挖掘一样。
                    location.getBlock().breakNaturally();
                    //得到周边区块玩家距离
                    TreeMap<Double, Player> playerDistanceTreeMap = PluginUtils.calculatePlayerAroundTheItem(location);
                    String recentPlayerDistanceName = "&c(未找到)";
                    //判断是否附近有玩家
                    if (PluginUtils.mapIsEmpty(playerDistanceTreeMap)){
                        recentPlayerDistanceName = playerDistanceTreeMap.pollFirstEntry().getValue().getName();
                    }
                    //广播消息
                    Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + destroyMessage.replaceAll("%player%", recentPlayerDistanceName).replaceAll("%x%", String.valueOf(location.getBlockX())).replaceAll("%y%", String.valueOf(location.getBlockY())).replaceAll("%z%", String.valueOf(location.getBlockZ()))));
                }
            });
        } else {
            //获取拥有能收到高频红石消息的玩家
            ArrayList<Player> players = PluginUtils.hasPermissionPlayerList(AntiHighFrequencyRedStoneEnum.RED_STONE_MESSAGE_PERMISSION.getCommand());
            //循环获取Value比对出现次数，超过出现次数就将该红石去除
            detectList.forEach((location, frequency) -> {
                //判断被登记的次数是否大于配置文件限制的次数
                if (frequency.compareTo(limit) >= 0) {
                    //普通破坏，就跟玩家挖掘一样。
                    location.getBlock().breakNaturally();
                    //得到周边区块玩家距离
                    TreeMap<Double, Player> playerDistanceTreeMap = PluginUtils.calculatePlayerAroundTheItem(location);
                    String recentPlayerDistanceName = "&c(未找到)";
                    //判断是否附近有玩家
                    if (!PluginUtils.mapIsEmpty(playerDistanceTreeMap)){
                        recentPlayerDistanceName = playerDistanceTreeMap.pollFirstEntry().getValue().getName();
                    }
                    //发送消息给有权限的用户
                    String finalRecentPlayerDistanceName = recentPlayerDistanceName;
                    players.forEach(player -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + destroyMessage.replaceAll("%player%", finalRecentPlayerDistanceName).replaceAll("%x%", String.valueOf(location.getBlockX())).replaceAll("%y%", String.valueOf(location.getBlockY())).replaceAll("%z%", String.valueOf(location.getBlockZ())))));
                }
            });
        }
        //判断完后清除所有数据
        detectList.clear();
    }


}
