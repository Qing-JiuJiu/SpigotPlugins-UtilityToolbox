package com.yishian.limithighaltitudewater;

import com.yishian.common.CommandEnum;
import com.yishian.common.PluginUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * @author XinQi
 */
public class LimitHighAltitudeWaterFlowListener implements Listener {

    @EventHandler
    public void onWaterLavaTo(BlockFromToEvent blockFromToEvent) {
        //得到配置文件
        FileConfiguration serverConfig = PluginUtils.getServerConfig();
        ConfigurationSection functionConfiguration = PluginUtils.getServerConfig().getConfigurationSection("limit-high-Altitude-water-flow");
        //被限制的世界
        List<String> limitWorldList = functionConfiguration.getStringList("limit-world-list");
        //被限制的流体
        List<String> limitFluidList = functionConfiguration.getStringList("limit-fluid-list");
        //获取限制最高高度
        int limitHigh = functionConfiguration.getInt("limit-high");
        //获取是否要广播消息
        boolean isBroadcastMessage = functionConfiguration.getBoolean("is-broadcast-message");

        //得到消息前缀和后缀
        String messagePrefix = serverConfig.getConfigurationSection("plugin-message").getString("message-prefix");
        String destroyMessage = functionConfiguration.getConfigurationSection("message").getString("destroy-message");

        //得到物品类
        Block block = blockFromToEvent.getBlock();
        
        //得到物品位置
        Location blockLocation = block.getLocation();
        int blockX = blockLocation.getBlockX();
        int blockY = blockLocation.getBlockY();
        int blockZ = blockLocation.getBlockZ();

        //先判断高度是否被限制
        if (blockY > limitHigh) {
            //判断是否是被限制的世界
            if (limitWorldList.contains(CommandEnum.ALL.getCommand()) || limitWorldList.contains(block.getWorld().getName())) {
                //循环流体限制
                if (limitFluidList.contains(block.getType().getKey().toString())) {
                    //禁止该方块的事件
                    blockFromToEvent.setCancelled(true);
                    //得到距离这个流体最近的玩家
                    TreeMap<Double, Player> playerDistanceTreeMap = PluginUtils.calculatePlayerAroundTheItem(blockLocation);
                    String recentPlayerDistanceName = playerDistanceTreeMap.pollFirstEntry().getValue().getName();
                    //发送消息内容模板
                    String messageTemplate = ChatColor.translateAlternateColorCodes('&', messagePrefix + destroyMessage.replaceAll("%player%", recentPlayerDistanceName).replaceAll("%x%", String.valueOf(blockX)).replaceAll("%y%", String.valueOf(blockY)).replaceAll("%z%", String.valueOf(blockZ)));
                    //判断是否要广播消息
                    if (isBroadcastMessage) {
                        //广播消息
                        Bukkit.getServer().broadcastMessage(messageTemplate);
                    } else {
                        //获取拥有能收到限制流体消息的玩家,并发送消息
                        ArrayList<Player> players = PluginUtils.hasPermissionPlayerList(LimitHighAltitudeWaterFlowEnum.LIMIT_FLOW_MESSAGE_PERMISSION.getCommand());
                        players.forEach(player -> player.sendMessage(messageTemplate));
                    }
                }
            }
        }
    }
}
