package com.yishian.command.fly;

import com.yishian.common.CommonEnum;
import com.yishian.common.CommonUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.List;


/**
 * @author XinQi
 */
public class FlyCommand implements TabExecutor {

    /**
     * 获取配置文件里该指令的消息提示
     */
    static ConfigurationSection flyMessage = CommonUtils.ServerConfig.getConfigurationSection(FlyEnum.FLY_COMMAND.getCommand()).getConfigurationSection(CommonEnum.MESSAGE.getCommand());

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
        //判断参数数量是否大于1个
        if (args.length > 1) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flyMessage.getString("fly-command-error")));
            return true;
        }

        //判断指令是否带参数
        if (args.length == 0) {
            //判断执行开启飞行的是自己还是控制台
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flyMessage.getString("fly-console-error")));
                return true;
            }
            Player player = (Player) sender;
            //判断玩家是否目前是飞行状态
            if (!player.getAllowFlight()) {
                player.setAllowFlight(true);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flyMessage.getString("fly-self-open").replaceAll("%player%", player.getName())));
            } else {
                player.setAllowFlight(false);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flyMessage.getString("fly-self-close").replaceAll("%player%", player.getName())));
            }
            //判断参数数量是否为1
        } else {
            //判断执行的是用户还是控制台
            if (sender instanceof Player) {
                Player player = (Player) sender;
                String playerName = player.getName();
                String othersPlayerName = args[0];
                //判断参数指向的是否是自己
                if (!playerName.equals(othersPlayerName)) {
                    //判断执行开关他人飞行指令的玩家权限
                    if (!player.hasPermission(FlyEnum.FLY_OTHERS_PERMISSION.getCommand()) && !player.isOp()) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flyMessage.getString("fly-others-no-permission")));
                        return true;
                    }
                    Player othersPlayer = Bukkit.getPlayerExact(othersPlayerName);
                    //判断玩家是否存在
                    if (othersPlayer == null) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flyMessage.getString("fly-others-no-exist").replaceAll("%others-player%", othersPlayerName)));
                        return true;
                    }
                    //判断该玩家是否在飞行
                    if (!othersPlayer.getAllowFlight()) {
                        othersPlayer.setAllowFlight(true);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flyMessage.getString("fly-others-open").replaceAll("%others-player%", othersPlayerName)));
                        othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flyMessage.getString("fly-by-others-open").replaceAll("%player%", playerName)));
                    } else {
                        othersPlayer.setAllowFlight(false);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flyMessage.getString("fly-others-close").replaceAll("%others-player%", othersPlayerName)));
                        othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flyMessage.getString("fly-by-others-close").replaceAll("%player%", playerName)));
                    }
                } else {
                    //判断玩家是否在飞行状态
                    if (!player.getAllowFlight()) {
                        player.setAllowFlight(true);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flyMessage.getString("fly-others-is-self-open").replaceAll("%player%", player.getName())));
                    } else {
                        player.setAllowFlight(false);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flyMessage.getString("fly-others-is-self-close").replaceAll("%player%", player.getName())));
                    }
                }
            } else {
                String othersPlayerName = args[0];
                Player othersPlayer = Bukkit.getPlayerExact(othersPlayerName);
                //判断玩家存不存在
                if (othersPlayer == null) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flyMessage.getString("fly-others-no-exist").replaceAll("%others-player%", othersPlayerName)));
                    return true;
                }
                //判断玩家是否在飞行状态
                if (!othersPlayer.getAllowFlight()) {
                    othersPlayer.setAllowFlight(true);
                    othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flyMessage.getString("fly-by-console-open")));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flyMessage.getString("fly-others-open").replaceAll("%others-player%", othersPlayerName)));
                } else {
                    othersPlayer.setAllowFlight(false);
                    othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flyMessage.getString("fly-by-console-close")));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flyMessage.getString("fly-others-close").replaceAll("%others-player%", othersPlayerName)));
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
        if (FlyEnum.FLY_COMMAND.getCommand().equalsIgnoreCase(label)) {
            return CommonUtils.arg1CommandPlayerTip(args);
        }
        return null;
    }
}
