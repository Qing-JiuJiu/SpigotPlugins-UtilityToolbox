package com.yishian.command.heal;

import com.yishian.common.CommonEnum;
import com.yishian.common.PluginUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.List;


/**
 * @author XinQi
 */
public class HealCommand implements TabExecutor {

    String healCommand = HealEnum.HEAL_COMMAND.getCommand();

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
        //获取配置文件里该指令的消息提示
        ConfigurationSection configurationSection = PluginUtils.getServerConfig();
        String messagePrefix = configurationSection.getConfigurationSection(CommonEnum.PLUGIN_MESSAGE.getCommand()).getString(CommonEnum.MESSAGE_PREFIX.getCommand());
        ConfigurationSection healMessage = configurationSection.getConfigurationSection(healCommand).getConfigurationSection(CommonEnum.MESSAGE.getCommand());

        //判断执行的指令内容
        if (healCommand.equalsIgnoreCase(label)) {
            //判断指令是否带参数
            if (args.length == 0) {
                //判断执行恢复自己指令的是用户还是控制台
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
                    player.setHealth(maxHealth);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + healMessage.getString("heal-self").replaceAll("%player%", player.getName())));
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + healMessage.getString("heal-console-error")));
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
                        if (player.hasPermission(HealEnum.HEAL_OTHERS_PERMISSION.getCommand())) {
                            Player othersPlayer = Bukkit.getPlayerExact(othersPlayerName);
                            //判断玩家是否存在
                            if (othersPlayer != null) {
                                othersPlayer.setHealth(othersPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + healMessage.getString("heal-others").replaceAll("%others-player%", othersPlayerName)));
                                othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + healMessage.getString("heal-by-others").replaceAll("%player%", playerName)));
                            } else {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + healMessage.getString("heal-others-no-exist").replaceAll("%others-player%", othersPlayerName)));
                            }
                        } else {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + healMessage.getString("heal-others-no-permission")));
                        }
                    } else {
                        //参数指向的是自己，恢复自己，并给出对应提示
                        double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
                        player.setHealth(maxHealth);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + healMessage.getString("heal-others-is-self").replaceAll("%player%", player.getName())));
                    }
                } else {
                    String othersPlayerName = args[0];
                    Player othersPlayer = Bukkit.getPlayerExact(othersPlayerName);
                    //判断该玩家是否存在
                    if (othersPlayer != null) {
                        othersPlayer.setHealth(othersPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                        othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + healMessage.getString("heal-by-console")));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + healMessage.getString("heal-others").replaceAll("%others-player%", othersPlayerName)));
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + healMessage.getString("heal-others-no-exist").replaceAll("%others-player%", othersPlayerName)));
                    }
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + healMessage.getString("heal-command-error")));
            }
            return true;
        }
        return false;
    }

    /**
     * 指令补全提示
     * @return 返回的提示内容
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        //判断指令是否是上面执行的指令
        if (healCommand.equalsIgnoreCase(label)) {
            return PluginUtils.arg1CommandPlayerTip(args);
        }
        return null;
    }
}
