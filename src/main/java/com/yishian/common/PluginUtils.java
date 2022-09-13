package com.yishian.common;

import com.google.common.collect.Lists;
import com.yishian.Main;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;


/**
 * @author XinQi
 */
public class PluginUtils {

    /**
     * 得到服务器配置文件
     *
     * @return 返回配置文件
     */
    public static FileConfiguration getServerConfig() {
        return Main.getProvidingPlugin(Main.class).getConfig();
    }

    /**
     * 参数数量最大为0-1时玩家通用提示
     *
     * @param args 指令参数
     * @return 提示
     */
    public static List<String> arg1CommandPlayerTip(String[] args) {
        ArrayList<String> tips = new ArrayList<>();
        //判断参数是否为空，是的话就给出全部提示
        if (StringUtils.isEmpty(args[0])) {
            Bukkit.getOnlinePlayers().forEach(player -> tips.add(player.getName()));
            //判断参数数量是否为1，证明输入了内容给出根据输入的参数前缀给出对应的提示
        } else if (args.length == 1) {
            Bukkit.getOnlinePlayers().forEach(player -> {
                String playerName = player.getName();
                if (playerName.startsWith(args[0])) {
                    tips.add(playerName);
                }
            });
        }
        return tips;
    }

    /**
     * 参数数量最大为1-2时玩家通用提示
     *
     * @param args 指令参数
     * @return 提示
     */
    public static List<String> arg2CommandPlayerTips(String[] args) {
        ArrayList<String> tips = new ArrayList<>();
        if (args.length == 2 && StringUtils.isEmpty(args[1])) {
            Bukkit.getOnlinePlayers().forEach(player -> tips.add(player.getName()));
            //判断参数数量是否为1，证明输入了内容给出根据输入的参数前缀给出对应的提示
        } else if (args.length == 2) {
            Bukkit.getOnlinePlayers().forEach(player -> {
                String playerName = player.getName();
                if (playerName.startsWith(args[1])) {
                    tips.add(playerName);
                }
            });
        }
        return tips;
    }

    /**
     * @param oneX 第一个位置的x坐标
     * @param oneY 第一个位置的y坐标
     * @param oneZ 第一个位置的z坐标
     * @param twoX 第二个位置的x坐标
     * @param twoY 第二个位置的y坐标
     * @param twoZ 第二个位置的z坐标
     * @return 返回计算后的距离
     * 该方案被移除，location提供了方法来计算距离
     */
    public static Double calculateDistance(Integer oneX, Integer oneY, Integer oneZ, Integer twoX, Integer twoY, Integer twoZ) {
        return (Math.pow((oneX - twoX), 2) + Math.pow((oneY - twoY), 2) + Math.pow((oneZ - twoZ), 2)) / 2;
    }

    /**
     * 计算一个物品中心区块+周边区块的玩家距离
     *
     * @param location 物品的位置
     * @return 计算后的以中心为区块加周边8个区块内所有玩家距离该物品的位置
     */
    public static TreeMap<Double, Player> calculatePlayerAroundTheItem(Location location) {
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

    /**
     * 获取拥有该权限的所有玩家列表
     * @param permission 权限
     * @return 返回拥有该权限的玩家列表
     */
    public static ArrayList<Player> hasPermissionPlayerList(String permission) {
        ArrayList<Player> players = new ArrayList<>();
        Bukkit.getServer().getOnlinePlayers().forEach(player -> {
            if (player.hasPermission(permission)) {
                players.add(player);
            }
        });
        return players;
    }

}
