package com.yishian.antihighfrequencyredstone;

import com.google.common.collect.Lists;
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
        if (functionConfiguration.getBoolean("is-broadcast-message")) {
            //循环获取Value比对出现次数，超过设置出现次数就将该红石去除
            detectList.forEach((location, frequency) -> {
                if (frequency.compareTo(limit) >= 0) {
                    //得到周边区块玩家距离
                    TreeMap<Double, Player> playerDistanceTreeMap = calculateRedStone(location);
                    //广播消息
                    Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + destroyMessage.replaceAll("%player%", playerDistanceTreeMap.pollFirstEntry().getValue().getName()).replaceAll("%x%", String.valueOf(Location.locToBlock(location.getX()))).replaceAll("%y%", String.valueOf(Location.locToBlock(location.getY()))).replaceAll("%z%", String.valueOf(Location.locToBlock(location.getZ())))));
                }
            });
        } else {
            //获取拥有能收到高频红石消息的玩家
            ArrayList<Player> players = new ArrayList<>();
            Bukkit.getServer().getOnlinePlayers().forEach(player -> {
                if (player.hasPermission(AntiHighFrequencyRedStoneEnum.RED_STONE_MESSAGE_PERMISSION.getCommand())) {
                    players.add(player);
                }
            });

            //循环获取Value比对出现次数，超过出现次数就将该红石去除
            detectList.forEach((location, frequency) -> {
                if (frequency.compareTo(limit) >= 0) {
                    //得到周边区块玩家距离
                    TreeMap<Double, Player> playerDistanceTreeMap = calculateRedStone(location);
                    //发送消息给有权限的用户
                    players.forEach(player -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + destroyMessage.replaceAll("%player%", playerDistanceTreeMap.pollFirstEntry().getValue().getName()).replaceAll("%x%", String.valueOf(Location.locToBlock(location.getX()))).replaceAll("%y%", String.valueOf(Location.locToBlock(location.getY()))).replaceAll("%z%", String.valueOf(Location.locToBlock(location.getZ()))))));
                }
            });
        }
        //判断完后清除所有数据
        detectList.clear();
    }

    /**
     * 计算中心区块+周边区块的玩家距离
     * @param location 红石被销毁的位置
     * @return 计算后的所有玩家距离红石的位置
     */
    public TreeMap<Double, Player> calculateRedStone(Location location) {
        //普通破坏，就跟玩家挖掘一样。
        location.getBlock().breakNaturally();
        //用于存储玩家，根据距离来得到玩家，方便排序
        TreeMap<Double, Player> playerDistanceTreeMap = new TreeMap<>();
        //一共要计算多少个位置，以中间为基础向周围8个区块计算有多少个玩家在内
        List<Location> locations = Lists.newArrayList();
        for (int i = 0; i < 360; i += 45) {
            // 转弧度制
            double radians = Math.toRadians(i);
            //添加距离中心坐标为圆心向周边16格外共计8个坐标，一个区块16*16
            locations.add(location.clone().add(16 * Math.cos(radians), 0D, 16 * Math.sin(radians)));
        }
        //添加中心坐标
        locations.add(location);

        //计算坐标内每个玩家跟被销毁红石的位置
        locations.forEach(detectLocation -> Arrays.stream(detectLocation.getChunk().getEntities()).forEach(entity -> {
            if (entity instanceof Player) {
                Player player = (Player) entity;
                Location playerLocation = player.getLocation();
                playerDistanceTreeMap.put(location.distanceSquared(playerLocation), player);
            }
        }));

        return playerDistanceTreeMap;
    }
}
