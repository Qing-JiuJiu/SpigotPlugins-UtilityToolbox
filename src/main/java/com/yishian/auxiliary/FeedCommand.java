package com.yishian.auxiliary;

import com.yishian.common.PluginUtils;
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
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //获取配置文件里该指令的消息提示
        ConfigurationSection configurationSection = PluginUtils.getServerConfig();
        String messagePrefix = configurationSection.getConfigurationSection("plugin-message").getString("message-prefix");
        ConfigurationSection feedMessage = configurationSection.getConfigurationSection("feed").getConfigurationSection("message");

        //判断执行的指令内容
        if (AuxiliaryCommandEnum.FEED_COMMAND.getCommand().equalsIgnoreCase(label)) {
            //判断指令是否带参数
            if (args.length == 0) {
                //判断执行恢复自己指令的是用户还是控制台
                if (sender instanceof Player) {
                    //恢复自己饱食度
                    Player player = (Player) sender;
                    player.setFoodLevel(20);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + feedMessage.getString("feed-self").replaceAll("%player%", player.getName())));
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + feedMessage.getString("feed-console-error")));
                }
                //判断参数数量是否为1
            } else if (args.length == 1) {
                //判断执行的是用户还是控制台
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    String othersPlayerName = args[0];
                    String playerName = player.getName();
                    //判断参数指向的是否是自己
                    if (!playerName.equals(othersPlayerName)) {
                        //判断执行恢复他人指令的玩家权限
                        if (player.hasPermission(AuxiliaryCommandEnum.FEED_OTHERS_PERMISSION.getCommand())) {
                            Player othersPlayer = Bukkit.getPlayerExact(othersPlayerName);
                            //判断玩家是否存在
                            if (othersPlayer != null) {
                                othersPlayer.setFoodLevel(20);
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + feedMessage.getString("feed-others").replaceAll("%others-player%", othersPlayerName)));
                                othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + feedMessage.getString("feed-by-others").replaceAll("%player%", playerName)));
                            } else {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + feedMessage.getString("feed-others-no-exist").replaceAll("%others-player%", othersPlayerName)));
                            }
                        } else {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + feedMessage.getString("feed-others-no-permission")));
                        }
                    } else {
                        //参数指向的是自己，恢复自己，并给出对应提示
                        player.setFoodLevel(20);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + feedMessage.getString("feed-others-is-self").replaceAll("%player%", player.getName())));
                    }
                } else {
                    String othersPlayerName = args[0];
                    Player othersPlayer = Bukkit.getPlayerExact(othersPlayerName);
                    //判断玩家是否存在
                    if (othersPlayer != null) {
                        othersPlayer.setFoodLevel(20);
                        othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + feedMessage.getString("feed-by-console")));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + feedMessage.getString("feed-others").replaceAll("%others-player%", othersPlayerName)));
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + feedMessage.getString("feed-others-no-exist").replaceAll("%others-player%", othersPlayerName)));
                    }
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + feedMessage.getString("feed-command-error")));
            }
            return true;
        }
        return false;
    }

    /**
     * 指令补全提示
     *
     * @param sender  Source of the command.  For players tab-completing a
     *                command inside of a command block, this will be the player, not
     *                the command block.
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    The arguments passed to the command, including final
     *                partial argument to be completed
     * @return 返回的提示内容
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        //判断指令是否是上面执行的指令
        if (AuxiliaryCommandEnum.FEED_COMMAND.getCommand().equalsIgnoreCase(label)) {
            return PluginUtils.arg1CommandPlayerTip(args);
        }
        return null;
    }
}
