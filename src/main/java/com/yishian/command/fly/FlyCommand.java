package com.yishian.command.fly;

import com.yishian.common.CommonUtils;
import com.yishian.common.PluginMessageConfigEnum;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author XinQi
 */
public class FlyCommand implements TabExecutor {

    /**
     * 指令设置
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return 返回的提示内容
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //如果是用户执行指令，不判断参数长度
        if (sender instanceof Player) {
            Player player = (Player) sender;
            //判断玩家是否目前是飞行状态
            if (!player.getAllowFlight()) {
                player.setAllowFlight(true);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', PluginMessageConfigEnum.MESSAGE_PREFIX.getMsg() + FlyConfigEnum.FLY_SELF_OPEN.getMsg()).replaceAll("%player%", player.getName()));
            } else {
                player.setAllowFlight(false);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', PluginMessageConfigEnum.MESSAGE_PREFIX.getMsg() + FlyConfigEnum.FLY_SELF_CLOSE.getMsg()).replaceAll("%player%", player.getName()));
            }
        } else {
            //控制台执行需要判断长度
            if (args.length == 0) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', PluginMessageConfigEnum.MESSAGE_PREFIX.getMsg() + FlyConfigEnum.FLY_CONSOLE_ERROR.getMsg()));
                return true;
            } else {
                String othersPlayerName = args[0];
                Player othersPlayer = Bukkit.getPlayerExact(othersPlayerName);
                //判断玩家存不存在
                if (othersPlayer == null) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', PluginMessageConfigEnum.MESSAGE_PREFIX.getMsg() + PluginMessageConfigEnum.PLAYER_NO_EXIST.getMsg()).replaceAll("%others-player%", othersPlayerName));
                    return true;
                }
                //判断玩家是否在飞行状态
                if (othersPlayer.getAllowFlight()) {
                    othersPlayer.setAllowFlight(false);
                    othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', PluginMessageConfigEnum.MESSAGE_PREFIX.getMsg() + FlyConfigEnum.FLY_BY_CONSOLE_CLOSE.getMsg()));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', PluginMessageConfigEnum.MESSAGE_PREFIX.getMsg() + FlyConfigEnum.FLY_OTHERS_CLOSE.getMsg()).replaceAll("%others-player%", othersPlayerName));
                } else {
                    othersPlayer.setAllowFlight(true);
                    othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', PluginMessageConfigEnum.MESSAGE_PREFIX.getMsg() + FlyConfigEnum.FLY_BY_CONSOLE_OPEN.getMsg()));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', PluginMessageConfigEnum.MESSAGE_PREFIX.getMsg() + FlyConfigEnum.FLY_OTHERS_OPEN.getMsg()).replaceAll("%others-player%", othersPlayerName));
                }
            }
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
        if (!(sender instanceof Player)) {
            return CommonUtils.arg1CommandPlayerTip(args);
        }
        return null;
    }
}
