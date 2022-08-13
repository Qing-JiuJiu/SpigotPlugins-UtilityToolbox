package com.yishian.auxiliary;

import com.yishian.Main;
import com.yishian.currency.ServerUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


/**
 * @author XinQi
 */
public class HealCommand implements TabExecutor {

    /**
     * 指令设置
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //获取配置文件
        ConfigurationSection configurationSection = ServerUtils.getServerConfig();
        String messagePrefix = configurationSection.getString("message-prefix");
        ConfigurationSection healMessage = configurationSection.getConfigurationSection("heal").getConfigurationSection("message");
        //恢复血量
        if (AuxiliaryCommandEnum.HEAL_COMMAND.getCommand().equalsIgnoreCase(label)) {
            if (args.length == 0) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    if (player.hasPermission(AuxiliaryCommandEnum.HEAL_PERMISSION.getCommand())) {
                        //获取最大生命值
                        double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
                        player.setHealth(maxHealth);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + healMessage.getString("heal-self").replaceAll("%player%", player.getName())));
                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + healMessage.getString("heal-no-permission").replaceAll("%player%", player.getName())));
                    }
                    return true;
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + healMessage.getString("heal-console-error")));
                }
            } else if (args.length == 1) {
                //恢复他人生命
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    if (player.hasPermission(AuxiliaryCommandEnum.HEAL_OTHERS_PERMISSION.getCommand())) {
                        String othersPlayerName = args[0];
                        Player othersPlayer = Bukkit.getPlayer(othersPlayerName);
                        othersPlayer.setHealth(othersPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + healMessage.getString("heal-others").replaceAll("%others-player%", othersPlayer.getName())));
                        othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + healMessage.getString("heal-by-others").replaceAll("%player%", player.getName())));
                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + healMessage.getString("heal-others-no-permission")));
                    }
                } else {
                    String othersPlayerName = args[0];
                    Player othersPlayer = Bukkit.getPlayer(othersPlayerName);
                    othersPlayer.setHealth(othersPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                    othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + healMessage.getString("heal-by-console")));
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
        if (AuxiliaryCommandEnum.HEAL_COMMAND.getCommand().equalsIgnoreCase(label)) {
            if (StringUtils.isEmpty(args[0])) {
                Bukkit.getOnlinePlayers().forEach(player -> tips.add(player.getName()));
                return tips;
            }
        } else {
            Bukkit.getOnlinePlayers().forEach(player -> {
                String playerName = player.getName();
                if (playerName.startsWith(args[0])) {
                    tips.add(playerName);
                }
            });
            return tips;
        }
        return null;
    }
}
