package com.yishian.function.preventhighfrequencyattacks;

import com.yishian.common.CommonEnum;
import com.yishian.common.CommonUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

/**
 * @author XinQi
 */
public class PreventHighFrequencyAttacksRunnable extends BukkitRunnable {

    /**
     * 得到配置文件相关信息
     */
    static ConfigurationSection functionConfiguration = CommonUtils.ServerConfig.getConfigurationSection("prevent-high-hrequency-attacks");

    /**
     * Key为玩家的UUID，Value为左键的次数
     */
    static HashMap<UUID, Integer> detectList = new HashMap<>();

    @Override
    public void run() {
         //得到限制次数
        int limit = functionConfiguration.getInt("limit");
        //得到是否踢出服务器
        boolean isKick = functionConfiguration.getBoolean("is-kill");
        //得到消息内容
        ConfigurationSection messageConfigurationSection = functionConfiguration.getConfigurationSection(CommonEnum.MESSAGE.getCommand());

        //判断是否要广播消息
        if (functionConfiguration.getBoolean(CommonEnum.IS_BROADCAST_MESSAGE.getCommand())) {
            //循环获取每个玩家的攻击次数
            detectList.forEach((uuid, frequency) -> {
                //如果大于限制次数就判断是否要踢出服务器
                if (frequency.compareTo(limit) >= 0) {
                    //出现异常的玩家
                    Player player = Bukkit.getPlayer(uuid);

                    //判断是否要踢出服务器，并给出对应消息
                    String message = isKick(isKick, messageConfigurationSection, frequency, player);

                    //广播消息并发送控制台消息
                    Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + message));
                    CommonUtils.consoleCommandSender.sendMessage(message);
                }
            });
        } else {
            //获取拥有能收到cps提醒消息的玩家
            ArrayList<Player> players = CommonUtils.hasPermissionPlayerList(PreventHighFrequencyAttacksEnum.CPS_MESSAGE_PERMISSION.getCommand());
            //循环获取每个玩家的攻击次数
            detectList.forEach((uuid, frequency) -> {
                //如果大于限制次数就踢出服务器
                if (frequency.compareTo(limit) >= 0) {
                    //出现异常的玩家
                    Player frequencyPlayer = Bukkit.getPlayer(uuid);

                    //判断是否要踢出服务器，并给出对应消息
                    String message = isKick(isKick, messageConfigurationSection, frequency, frequencyPlayer);

                    //发送消息给有权限的人并发送给控制台
                    players.forEach(player -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + message)));
                    CommonUtils.consoleCommandSender.sendMessage(message);
                }
            });
        }

        //判断完后清除所有数据
        detectList.clear();
    }

    public String isKick(boolean isKick, ConfigurationSection messageConfigurationSection, Integer frequency, Player player) {
        //判断是否要踢出服务器，并返回对应消息
        if (isKick) {
            //踢出玩家并组织消息
            player.kickPlayer(ChatColor.translateAlternateColorCodes('&', messageConfigurationSection.getString("player-kick-message").replaceAll("%cps%", frequency.toString())));
            return messageConfigurationSection.getString("broadcast-kick-message").replaceAll("%player%", player.getName()).replaceAll("%cps%", frequency.toString());
        } else {
            //组织消息
            return messageConfigurationSection.getString("broadcast-no-kick-message").replaceAll("%player%", player.getName()).replaceAll("%cps%", frequency.toString());
        }
    }
}
