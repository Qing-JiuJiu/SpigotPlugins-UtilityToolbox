package com.yishian.antihighfrequencyredstone;

import com.yishian.common.PluginUtils;
import org.bukkit.Bukkit;
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
        FileConfiguration serverConfig = PluginUtils.getServerConfig();
        ConfigurationSection functionConfiguration = serverConfig.getConfigurationSection("anti-high-frequency-red-stone");
        int limit = functionConfiguration.getInt("limit");
        String messagePrefix = serverConfig.getConfigurationSection("plugin-message").getString("message-prefix");
        String destroyMessage = functionConfiguration.getConfigurationSection("message").getString("destroy-message");
        //判断是否要广播消息
        if (functionConfiguration.getBoolean("is-broadcast-message")) {
            //循环获取Value比对出现次数，超过出现次数就将该红石去除
            detectList.forEach((location, frequency) -> {
                if (frequency.compareTo(limit) >= 0) {
                    //普通破坏，就跟玩家挖掘一样。
                    location.getBlock().breakNaturally();
                    //用于存储玩家，根据距离来得到玩家，方便排序
                    TreeMap<Double, Player> playerDistanceTreeMap = new TreeMap<>();
                    //计算每个玩家跟被销毁红石的位置
                    Arrays.stream(location.getChunk().getEntities()).forEach(entity -> {
                        if (entity instanceof Player) {
                            Player player = (Player) entity;
                            Location playerLocation = player.getLocation();
                            Double distance = PluginUtils.calculateDistance(playerLocation.getX(), playerLocation.getY(), playerLocation.getZ(), location.getX(), location.getY(), location.getZ());
                            playerDistanceTreeMap.put(distance, player);
                        }
                    });
                    Player player = playerDistanceTreeMap.pollFirstEntry().getValue();
                    //TODO 发送消息
                    Bukkit.getServer().broadcastMessage(messagePrefix + destroyMessage);
                }
            });
        } else {
            ArrayList<Player> players = new ArrayList<>();
            Bukkit.getServer().getOnlinePlayers().forEach(player -> {
                if (player.hasPermission(AntiHighFrequencyRedStoneEnum.RED_STONE_MESSAGE_PERMISSION.getCommand())) {
                    players.add(player);
                }
            });
            //循环获取Value比对出现次数，超过出现次数就将该红石去除
            detectList.forEach((location, frequency) -> {
                if (frequency.compareTo(limit) >= 0) {
                    //普通破坏，就跟玩家挖掘一样。
                    location.getBlock().breakNaturally();
                    //TODO 判断距离 发送消息给有权限的用户
                }
            });

        }

        //判断完后清除所有数据
        detectList.clear();
    }
}
