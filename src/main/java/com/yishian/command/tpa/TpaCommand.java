package com.yishian.command.tpa;

import com.yishian.common.CommonEnum;
import com.yishian.common.CommonUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author XinQi
 */
public class TpaCommand implements TabExecutor {
    /**
     * 用于存储每个玩家自身被别人传送列表信息
     */
    public static HashMap<Player, Set<Player>> transfeMap = new HashMap<>();

    /**
     * 用于记录自己是否有一个传送请求
     */
    public static HashMap<Player, Player> transfeRecordMap = new HashMap<>();

    /**
     * 指令设置
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //判断参数是否大于1
        if (args.length > 1) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + TpaConfigEnum.TPA_COMMAND_ERROR.getMsg()));
            return true;
        }

        //判断指令是否带参数
        if (args.length == 0) {
            //判断执行指令是用户还是控制台
            if (sender instanceof Player) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + TpaConfigEnum.TPA_COMMAND_ERROR.getMsg()));
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + TpaConfigEnum.TPA_CONSOLE_ERROR.getMsg()));
            }
            //判断参数数量是否为1
        } else {
            //判断执行的是用户还是控制台
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + TpaConfigEnum.TPA_CONSOLE_ERROR.getMsg()));
                return true;
            }
            Player player = (Player) sender;
            String playerName = player.getName();
            String othersPlayerName = args[0];
            //判断传送的名字是否是自己
            if (playerName.equals(othersPlayerName)) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + TpaConfigEnum.TPA_COMMAND_ERROR.getMsg()));
                return true;
            }
            Player othersPlayer = Bukkit.getPlayerExact(othersPlayerName);
            //判断玩家是否存在
            if (othersPlayer == null) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + TpaConfigEnum.TPA_OTHERS_NO_EXIST.getMsg().toString().replaceAll("%others-player%", othersPlayerName)));
                return true;
            }
            //添加传送信息
            Set<Player> playerSet = transfeMap.get(othersPlayer);
            //判断该列表是否为空
            if (playerSet != null) {
                playerSet.add(player);
            } else {
                playerSet = new HashSet<>();
                playerSet.add(player);
            }
            //判断是否是相同传送
            Player recordPlayer = transfeRecordMap.get(player);
            //如果相同将不重复发送，如果不相同将自动取消上一个传送请求
            if (recordPlayer == othersPlayer) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + TpaConfigEnum.TPA_OTHERS_IDENTICAL.getMsg()));
                return true;
            } else if (recordPlayer != null) {
                transfeMap.get(recordPlayer).removeIf(judgePlayer -> player == judgePlayer);
                recordPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + TpaConfigEnum.TPA_AUTO_TPACANCEL.getMsg()));
                othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + TpaConfigEnum.TPA_AUTO_TPACANCEL_OTHERS.getMsg().toString().replaceAll("%player%", playerName)));
            }

            //添加至传送列表
            transfeMap.put(othersPlayer, playerSet);
            //添加自身传送信息
            transfeRecordMap.put(player, othersPlayer);
            //发送相关提醒
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + TpaConfigEnum.TPA_APPLY.getMsg().toString().replaceAll("%others-player%", othersPlayerName)));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + TpaConfigEnum.TPA_APPLY_TPACANCEL_TIPS.getMsg()));
            othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + TpaConfigEnum.TPA_APPLY_OTHERS.getMsg().toString().replaceAll("%player%", playerName)));
            othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + TpaConfigEnum.TPA_APPLY_ACCEPT_TIPS.getMsg()));
            othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + TpaConfigEnum.TPA_APPLY_DENY_TIPS.getMsg()));


        }
        return true;
    }

    /**
     * 指令提示
     *
     * @return 返回的提示内容
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        //判断指令是否是上面执行的指令
        if (TpaEnum.TPA_COMMAND.getCommand().equalsIgnoreCase(label)) {
            return CommonUtils.arg1CommandPlayerTip(args, sender);
        }
        return null;
    }
}
