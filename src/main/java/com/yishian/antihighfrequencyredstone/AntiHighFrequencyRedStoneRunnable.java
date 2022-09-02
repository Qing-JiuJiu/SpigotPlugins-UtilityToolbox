package com.yishian.antihighfrequencyredstone;

import com.yishian.common.CommandEnum;
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
        String messagePrefix = serverConfig.getConfigurationSection("plugin-message").getString("message-prefix");
        String destroyMessage = functionConfiguration.getConfigurationSection("message").getString("destroy-message");

        //判断是否要广播消息
        if (functionConfiguration.getBoolean(CommandEnum.IS_BROADCAST_MESSAGE.getCommand())) {
            //循环获取Value比对出现次数，超过设置出现次数就将该红石去除
            detectList.forEach((location, frequency) -> {
                if (frequency.compareTo(limit) >= 0) {
                    //普通破坏，就跟玩家挖掘一样。
                    location.getBlock().breakNaturally();
                    //得到周边区块玩家距离
                    TreeMap<Double, Player> playerDistanceTreeMap = PluginUtils.calculatePlayerAroundTheItem(location);
                    //广播消息
                    Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + destroyMessage.replaceAll("%player%", playerDistanceTreeMap.pollFirstEntry().getValue().getName()).replaceAll("%x%", String.valueOf(location.getBlockX())).replaceAll("%y%", String.valueOf(location.getBlockY())).replaceAll("%z%", String.valueOf(location.getBlockZ()))));
                }
            });
        } else {
            //获取拥有能收到高频红石消息的玩家
            ArrayList<Player> players = PluginUtils.hasPermissionPlayerList(AntiHighFrequencyRedStoneEnum.RED_STONE_MESSAGE_PERMISSION.getCommand());

            //循环获取Value比对出现次数，超过出现次数就将该红石去除
            detectList.forEach((location, frequency) -> {
                if (frequency.compareTo(limit) >= 0) {
                    //普通破坏，就跟玩家挖掘一样。
                    location.getBlock().breakNaturally();
                    //得到周边区块玩家距离
                    TreeMap<Double, Player> playerDistanceTreeMap = PluginUtils.calculatePlayerAroundTheItem(location);
                    //发送消息给有权限的用户
                    players.forEach(player -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + destroyMessage.replaceAll("%player%", playerDistanceTreeMap.pollFirstEntry().getValue().getName()).replaceAll("%x%", String.valueOf(location.getBlockX())).replaceAll("%y%", String.valueOf(location.getBlockY())).replaceAll("%z%", String.valueOf(location.getBlockZ())))));
                }
            });
        }
        //判断完后清除所有数据
        detectList.clear();
    }


}
