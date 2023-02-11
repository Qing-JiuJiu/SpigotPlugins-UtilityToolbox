package com.yishian.function.preventhighfrequencyattacks;

import com.yishian.common.CommonEnum;
import com.yishian.common.CommonUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author XinQi
 */
public class PreventHighFrequencyAttacksRunnable extends BukkitRunnable {
    /**
     * Key为玩家的UUID，Value为左键的次数
     */
    static HashMap<UUID, Integer> detectList = new HashMap<>();

    @Override
    public void run() {
         //得到限制次数
        Integer limit = (Integer) PreventHighFrequencyAttacksConfigEnum.LIMIT.getMsg();
        //得到是否踢出服务器
        boolean isKick = (Boolean)PreventHighFrequencyAttacksConfigEnum.IS_KILL.getMsg();

        //判断是否要广播消息
        if ((Boolean) PreventHighFrequencyAttacksConfigEnum.IS_BROADCAST_MESSAGE.getMsg()) {
            //循环获取每个玩家的攻击次数
            detectList.forEach((uuid, frequency) -> {
                //如果大于限制次数就判断是否要踢出服务器
                if (frequency.compareTo(limit) >= 0) {
                    //出现异常的玩家
                    Player player = Bukkit.getPlayer(uuid);

                    //判断是否要踢出服务器，并给出对应消息
                    String message = isKick(isKick, frequency, player);

                    //广播消息并打印日志
                    Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + message));
                    CommonUtils.javaPlugin.getLogger().warning(message);
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
                    String message = isKick(isKick, frequency, frequencyPlayer);

                    //发送消息给有权限的人并发送给控制台
                    players.forEach(player -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + message)));
                    CommonUtils.consoleCommandSender.sendMessage(message);
                }
            });
        }

        //判断完后清除所有数据
        detectList.clear();
    }

    public String isKick(boolean isKick, Integer frequency, Player player) {
        //判断是否要踢出服务器，并返回对应消息
        if (isKick) {
            //踢出玩家并组织消息
            player.kickPlayer(ChatColor.translateAlternateColorCodes('&', PreventHighFrequencyAttacksConfigEnum.PLAYER_KICK_MESSAGE.getMsg().toString().replaceAll("%cps%", frequency.toString())));
            return PreventHighFrequencyAttacksConfigEnum.BROADCAST_KICK_MESSAGE.getMsg().toString().replaceAll("%player%", player.getName()).replaceAll("%cps%", frequency.toString());
        } else {
            //组织消息
            return PreventHighFrequencyAttacksConfigEnum.BROADCAST_NO_KICK_MESSAGE.getMsg().toString().replaceAll("%player%", player.getName()).replaceAll("%cps%", frequency.toString());
        }
    }
}
