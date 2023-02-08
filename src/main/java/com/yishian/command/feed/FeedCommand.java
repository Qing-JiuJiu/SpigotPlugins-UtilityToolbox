package com.yishian.command.feed;

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
public class FeedCommand implements TabExecutor {

    /**
     * 获取配置文件里该指令的消息提示
     */
    static ConfigurationSection feedMessage = CommonUtils.ServerConfig.getConfigurationSection(FeedEnum.FEED_COMMAND.getCommand()).getConfigurationSection(CommonEnum.MESSAGE.getCommand());

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //判断指令参数是否超过2个
        if (args.length > 1) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + feedMessage.getString("feed-command-error")));
            return true;
        }

        //判断指令是否带参数
        if (args.length == 0) {
            //判断执行恢复自己指令的是用户还是控制台
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + feedMessage.getString("feed-console-error")));
                return true;
            }
            //恢复自己饱食度
            Player player = (Player) sender;
            player.setFoodLevel(20);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + feedMessage.getString("feed-self").replaceAll("%player%", player.getName())));
            //否则参数数量是为1，得到这个参数
        } else {
            //判断执行的是用户还是控制台
            if (sender instanceof Player) {
                Player player = (Player) sender;
                String othersPlayerName = args[0];
                String playerName = player.getName();
                //判断参数指向的是否是自己
                if (!playerName.equals(othersPlayerName)) {
                    //判断执行恢复他人指令的玩家权限
                    if (!player.hasPermission(FeedEnum.FEED_OTHERS_PERMISSION.getCommand()) && !player.isOp()) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + feedMessage.getString("feed-others-no-permission")));
                        return true;
                    }
                    Player othersPlayer = Bukkit.getPlayerExact(othersPlayerName);
                    //判断玩家是否存在
                    if (othersPlayer == null) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + feedMessage.getString("feed-others-no-exist").replaceAll("%others-player%", othersPlayerName)));
                        return true;
                    }
                    othersPlayer.setFoodLevel(20);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + feedMessage.getString("feed-others").replaceAll("%others-player%", othersPlayerName)));
                    othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + feedMessage.getString("feed-by-others").replaceAll("%player%", playerName)));
                } else {
                    //参数指向的是自己，恢复自己，并给出对应提示
                    player.setFoodLevel(20);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + feedMessage.getString("feed-others-is-self").replaceAll("%player%", player.getName())));
                }
            } else {
                String othersPlayerName = args[0];
                Player othersPlayer = Bukkit.getPlayerExact(othersPlayerName);
                //判断玩家是否存在
                if (othersPlayer == null) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + feedMessage.getString("feed-others-no-exist").replaceAll("%others-player%", othersPlayerName)));
                    return true;
                }
                othersPlayer.setFoodLevel(20);
                othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + feedMessage.getString("feed-by-console")));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + feedMessage.getString("feed-others").replaceAll("%others-player%", othersPlayerName)));
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
        if (FeedEnum.FEED_COMMAND.getCommand().equalsIgnoreCase(label)) {
            return CommonUtils.arg1CommandPlayerTip(args);
        }
        return null;
    }
}
