package com.yishian.function.antihighfrequencyredstone;

import com.yishian.common.CommonEnum;
import com.yishian.common.CommonUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

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
        //得到限制列表
        List<?> anitList = CommonUtils.objectToList(AntiHighFrequencyRedStoneConfigEnum.ANTI_RED_STONE_LIST.getMsg());
        //得到限制次数
        Integer limit = (Integer) AntiHighFrequencyRedStoneConfigEnum.LIMIT.getMsg();

        //判断是否要广播消息
        if ((Boolean) AntiHighFrequencyRedStoneConfigEnum.IS_BROADCAST_MESSAGE.getMsg()) {
            //循环获取Value比对出现次数，超过设置出现次数就将该红石去除
            detectList.forEach((location, frequency) -> {
                if (frequency.compareTo(limit) >= 0) {
                    //得到距离该位置最近的玩家
                    TreeMap<Double, Player> playerDistanceTreeMap = getDoublePlayerTreeMap(anitList, location);

                    String recentPlayerDistanceName = "&c(未找到)";
                    //判断是否附近有玩家
                    if (CommonUtils.mapIsEmpty(playerDistanceTreeMap)) {
                        recentPlayerDistanceName = playerDistanceTreeMap.pollFirstEntry().getValue().getName();
                    }
                    //广播消息
                    String message = AntiHighFrequencyRedStoneConfigEnum.DESTROY_MESSAGE.getMsg().toString().replaceAll("%player%", recentPlayerDistanceName).replaceAll("%x%", String.valueOf(location.getBlockX())).replaceAll("%y%", String.valueOf(location.getBlockY())).replaceAll("%z%", String.valueOf(location.getBlockZ()));
                    Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + message));

                    //打印警告日志
                    CommonUtils.javaPlugin.getLogger().warning(message);
                }
            });
        } else {
            //获取拥有能收到高频红石消息的玩家
            ArrayList<Player> players = CommonUtils.hasPermissionPlayerList(AntiHighFrequencyRedStoneEnum.RED_STONE_MESSAGE_PERMISSION.getCommand());
            //循环获取Value比对出现次数，超过出现次数就将该红石去除
            detectList.forEach((location, frequency) -> {
                //判断被登记的次数是否大于配置文件限制的次数
                if (frequency.compareTo(limit) >= 0) {
                    //识别是否还是红石列表，如果是就摧毁
                    TreeMap<Double, Player> playerDistanceTreeMap = getDoublePlayerTreeMap(anitList, location);
                    //初始最近的玩家字符串
                    String recentPlayerDistanceName = "&c(未找到)";
                    //判断是否附近有玩家
                    if (!CommonUtils.mapIsEmpty(playerDistanceTreeMap)) {
                        recentPlayerDistanceName = playerDistanceTreeMap.pollFirstEntry().getValue().getName();
                    }
                    //发送消息给有权限的用户
                    String finalRecentPlayerDistanceName = recentPlayerDistanceName;
                    players.forEach(player -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + AntiHighFrequencyRedStoneConfigEnum.DESTROY_MESSAGE.getMsg().toString().replaceAll("%player%", finalRecentPlayerDistanceName).replaceAll("%x%", String.valueOf(location.getBlockX())).replaceAll("%y%", String.valueOf(location.getBlockY())).replaceAll("%z%", String.valueOf(location.getBlockZ())))));

                    //发送控制台
                    String message = AntiHighFrequencyRedStoneConfigEnum.DESTROY_MESSAGE.getMsg().toString().replaceAll("%player%", recentPlayerDistanceName).replaceAll("%x%", String.valueOf(location.getBlockX())).replaceAll("%y%", String.valueOf(location.getBlockY())).replaceAll("%z%", String.valueOf(location.getBlockZ()));
                    CommonUtils.sendConsoleMessage(message);
                }
            });
        }
        //判断完后清除所有数据
        detectList.clear();
    }

    /**
     * 得到距离该位置最近的玩家
     */
    private static TreeMap<Double, Player> getDoublePlayerTreeMap(List<?> anitList, Location location) {
        //识别是否还是红石列表，如果是就摧毁
        Block block = location.getBlock();
        //获得物品名字
        String blockName = block.getType().getKey().toString();
        if (anitList.contains(blockName)) {
            //普通破坏，就跟玩家挖掘一样。
            block.breakNaturally();
        }

        //得到周边区块玩家距离
        return CommonUtils.calculatePlayerAroundTheItem(location);
    }


}
