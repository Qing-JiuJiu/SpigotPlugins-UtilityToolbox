package com.yishian.auxiliary;

import com.yishian.common.ServerUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
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
public class HealAndFeedCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //获取配置文件里该指令的消息提示
        ConfigurationSection configurationSection = ServerUtils.getServerConfig();
        String messagePrefix = configurationSection.getConfigurationSection("plugin-message").getString("message-prefix");
        ConfigurationSection healAndFeedMessage = configurationSection.getConfigurationSection("healandfeed").getConfigurationSection("message");

        //判断执行的指令内容
        if (AuxiliaryCommandEnum.HEAL_AND_FEED_COMMAND.getCommand().equalsIgnoreCase(label)) {
            //判断指令是否带参数
            if (args.length == 0) {
                //判断执行恢复自己指令的是用户还是控制台
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                    player.setFoodLevel(20);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + healAndFeedMessage.getString("heal-and-feed-self").replaceAll("%player%", player.getName())));
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + healAndFeedMessage.getString("heal-and-feed-console-error")));
                }
                //判断参数数量是否为1
            } else if (args.length == 1) {
                //判断执行的是用户还是控制台
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    String playerName = player.getName();
                    String othersPlayerName = args[0];
                    //判断参数指向的是否是自己
                    if (!playerName.equals(othersPlayerName)) {
                        //判断执行恢复他人指令的玩家权限
                        if (player.hasPermission(AuxiliaryCommandEnum.HEAL_OTHERS_PERMISSION.getCommand()) && player.hasPermission(AuxiliaryCommandEnum.FEED_OTHERS_PERMISSION.getCommand())) {
                            Player othersPlayer = Bukkit.getPlayer(othersPlayerName);
                            //判断玩家是否存在
                            if (othersPlayer != null) {
                                othersPlayer.setHealth(othersPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                                othersPlayer.setFoodLevel(20);
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + healAndFeedMessage.getString("heal-and-feed-others").replaceAll("%others-player%", othersPlayerName)));
                                othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + healAndFeedMessage.getString("heal-and-feed-by-others").replaceAll("%player%", playerName)));
                            } else {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + healAndFeedMessage.getString("heal-and-feed-no-exist").replaceAll("%others-player%", othersPlayerName)));
                            }
                        } else {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + healAndFeedMessage.getString("heal-and-feed-others-no-permission")));
                        }
                    } else {
                        //参数指向的是自己，恢复自己，并给出对应提示
                        player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                        player.setFoodLevel(20);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + healAndFeedMessage.getString("heal-and-feed-others-is-self").replaceAll("%player%", player.getName())));
                    }
                } else {
                    String othersPlayerName = args[0];
                    Player othersPlayer = Bukkit.getPlayer(othersPlayerName);
                    if (othersPlayer != null) {
                        othersPlayer.setHealth(othersPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                        othersPlayer.setFoodLevel(20);
                        othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + healAndFeedMessage.getString("heal-and-feed-by-console")));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + healAndFeedMessage.getString("heal-and-feed-others").replaceAll("%others-player%", othersPlayerName)));
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + healAndFeedMessage.getString("heal-and-feed-no-exist").replaceAll("%others-player%", othersPlayerName)));
                    }
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + healAndFeedMessage.getString("heal-and-feed-command-error")));
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
        List<String> tips = new ArrayList<>();
        //判断指令是否是上面执行的指令
        if (AuxiliaryCommandEnum.HEAL_AND_FEED_COMMAND.getCommand().equalsIgnoreCase(label)) {
            //判断参数是否为空，是的话就给出全部提示
            if (StringUtils.isEmpty(args[0])) {
                Bukkit.getOnlinePlayers().forEach(player -> tips.add(player.getName()));
                return tips;
                //判断参数数量是否为1，证明输入了内容给出根据输入的参数前缀给出对应的提示
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
