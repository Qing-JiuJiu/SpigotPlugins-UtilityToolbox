package com.yishian.common;

import com.google.common.collect.Lists;
import com.yishian.Main;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


/**
 * @author XinQi
 */
public class CommonUtils {

    /**
     * 服务器配置文件
     */
    public static FileConfiguration ServerConfig = Main.getProvidingPlugin(Main.class).getConfig();

    /**
     * 控制台
     */
    public static ConsoleCommandSender consoleCommandSender = Bukkit.getConsoleSender();

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
     * 参数数量最大为0-1时玩家通用提示(排除自己)
     *
     * @param args          指令参数
     * @param excludePlayer 排除的提示
     * @return 提示
     */
    public static List<String> arg1CommandPlayerTip(String[] args, CommandSender excludePlayer) {
        ArrayList<String> tips = new ArrayList<>();
        //判断参数是否为空，是的话就给出全部提示
        if (StringUtils.isEmpty(args[0])) {
            Bukkit.getOnlinePlayers().forEach(player -> {
                if (excludePlayer != player) {
                    tips.add(player.getName());
                }
            });
            //判断参数数量是否为1，证明输入了内容给出根据输入的参数前缀给出对应的提示
        } else if (args.length == 1) {
            Bukkit.getOnlinePlayers().forEach(player -> {
                String playerName = player.getName();
                if (excludePlayer != player && playerName.startsWith(args[0])) {
                    tips.add(playerName);
                }
            });
        }
        return tips;
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
     *
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

    /**
     * 判断集合是否为空
     */
    public static boolean collectionIsEmpty(Collection<?> list) {
        if (list == null || list.isEmpty()) {
            return true;
        }
        return list.size() == 1 && list.removeIf(judgelist -> judgelist == "");
    }

    /**
     * 判断Map是否为空
     */
    public static boolean mapIsEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 根据Set<Player>来提示
     */
    public static List<String> playerSetToTips(String[] args, Set<Player> playerSet) {
        ArrayList<String> tips = new ArrayList<>();
        //判断参数是否为空，是的话就给出全部提示
        if (StringUtils.isEmpty(args[0])) {
            playerSet.forEach(player -> tips.add(player.getName()));
            //判断参数数量是否为1，证明输入了内容给出根据输入的参数前缀给出对应的提示
        } else if (args.length == 1) {
            playerSet.forEach(player -> {
                String playerName = player.getName();
                if (playerName.startsWith(args[0])) {
                    tips.add(playerName);
                }
            });
        }
        return tips;
    }

    /**
     * 根据List<String>来提示
     */
    public static List<String> tipsListToTips(String[] args, List<String> tipsList) {
        ArrayList<String> tips = new ArrayList<>();
        //判断参数是否为空，是的话就给出全部提示
        if (StringUtils.isEmpty(args[0])) {
            return tipsList;
            //判断参数数量是否为1，证明输入了内容给出根据输入的参数前缀给出对应的提示
        } else if (args.length == 1) {
            tipsList.forEach(tip -> {
                if (tip.startsWith(args[0])) {
                    tips.add(tip);
                }
            });
        }
        return tips;
    }

    /**
     * &替换成$,用于特殊颜色演示
     */
    public static String replaceColorCode(String stringReplace) {
        stringReplace = stringReplace.replace("&0", "§0");
        stringReplace = stringReplace.replace("&1", "§1");
        stringReplace = stringReplace.replace("&2", "§2");
        stringReplace = stringReplace.replace("&3", "§3");
        stringReplace = stringReplace.replace("&4", "§4");
        stringReplace = stringReplace.replace("&5", "§5");
        stringReplace = stringReplace.replace("&6", "§6");
        stringReplace = stringReplace.replace("&7", "§7");
        stringReplace = stringReplace.replace("&8", "§8");
        stringReplace = stringReplace.replace("&9", "§9");
        stringReplace = stringReplace.replace("&a", "§a");
        stringReplace = stringReplace.replace("&b", "§b");
        stringReplace = stringReplace.replace("&c", "§c");
        stringReplace = stringReplace.replace("&d", "§d");
        stringReplace = stringReplace.replace("&e", "§e");
        stringReplace = stringReplace.replace("&f", "§f");
        stringReplace = stringReplace.replace("&k", "§k");
        stringReplace = stringReplace.replace("&l", "§l");
        stringReplace = stringReplace.replace("&m", "§m");
        stringReplace = stringReplace.replace("&n", "§n");
        stringReplace = stringReplace.replace("&o", "§o");
        stringReplace = stringReplace.replace("&r", "§r");
        return stringReplace;
    }

    /**
     * 向控制台发送消息
     */
    public static void sendConsoleMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX + message));
    }

    /**
     * 向配置文件写入内容
     *
     * @param yamlContent        需要写入的yaml内容
     * @param path 需要写入的文件路径
     */
    public static void saveYamlConfig(YamlConfiguration yamlContent, Path path) {
        try {
            Writer writer = new OutputStreamWriter(Files.newOutputStream(path), StandardCharsets.UTF_8);
            writer.write(yamlContent.saveToString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
