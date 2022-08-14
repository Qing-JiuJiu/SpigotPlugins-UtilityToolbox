package com.yishian.auxiliary;

import com.yishian.common.ServerUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XinQi
 */
public class FeedCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //获取配置文件
        ConfigurationSection configurationSection = ServerUtils.getServerConfig();
        String messagePrefix = configurationSection.getConfigurationSection("plugin-message").getString("message-prefix");
        ConfigurationSection feedMessage = configurationSection.getConfigurationSection("feed").getConfigurationSection("message");

        if (AuxiliaryCommandEnum.FEED_COMMAND.getCommand().equalsIgnoreCase(label)) {
            if (args.length == 0) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    if (player.hasPermission(AuxiliaryCommandEnum.FEED_PERMISSION.getCommand())) {
                        player.setFoodLevel(20);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + feedMessage.getString("feed-self").replaceAll("%player%", player.getName())));
                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + feedMessage.getString("feed-no-permission").replaceAll("%player%", player.getName())));
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + feedMessage.getString("feed-console-error")));
                }
            } else if (args.length == 1) {
                //治愈他人生命
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    if (player.hasPermission(AuxiliaryCommandEnum.FEED_OTHERS_PERMISSION.getCommand())) {
                        String othersPlayerName = args[0];
                        Player othersPlayer = Bukkit.getPlayer(othersPlayerName);
                        othersPlayer.setFoodLevel(20);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + feedMessage.getString("feed-others").replaceAll("%others-player%", othersPlayer.getName())));
                        othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + feedMessage.getString("feed-by-others").replaceAll("%player%", player.getName())));
                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + feedMessage.getString("feed-others-no-permission")));
                    }
                } else {
                    String othersPlayerName = args[0];
                    Player othersPlayer = Bukkit.getPlayer(othersPlayerName);
                    othersPlayer.setFoodLevel(20);
                    othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + feedMessage.getString("feed-by-console")));
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
     * @return
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> tips = new ArrayList<>();
        if (AuxiliaryCommandEnum.FEED_COMMAND.getCommand().equalsIgnoreCase(label)) {
            if (StringUtils.isEmpty(args[0])) {
                Bukkit.getOnlinePlayers().forEach(player -> tips.add(player.getName()));
                return tips;
            } else if (args.length == 1) {
                Bukkit.getOnlinePlayers().forEach(player -> {
                    String playerName = player.getName();
                    if (playerName.startsWith(args[0])) {
                        tips.add(playerName);
                    }
                });
                return tips;
            }
        }
        return null;
    }
}
