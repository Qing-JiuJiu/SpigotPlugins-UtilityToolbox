package com.yishian.command.teleport;


import com.yishian.common.CommonEnum;
import com.yishian.common.PluginUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;


/**
 * @author XinQi
 */
public class TeleportCommand implements CommandExecutor {

    String teleportCommand = TeleportEnum.TELEPORT_COMMAND.getCommand();

    public static boolean allowTp = true;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //获取配置文件里该指令的消息提示
        ConfigurationSection configurationSection = PluginUtils.getServerConfig();
        String messagePrefix = configurationSection.getConfigurationSection(CommonEnum.PLUGIN_MESSAGE.getCommand()).getString(CommonEnum.MESSAGE_PREFIX.getCommand());
        ConfigurationSection teleportMessage = configurationSection.getConfigurationSection(teleportCommand).getConfigurationSection(CommonEnum.MESSAGE.getCommand());

            //判断指令是否带参数
            if (args.length == 0) {
                if (allowTp) {
                    allowTp = false;
                    Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + teleportMessage.getString("teleport-deny")));
                } else {
                    allowTp = true;
                    Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + teleportMessage.getString("teleport-allow")));
                }

                //判断指令是否带一个参数，这个参数就是原因
            } else if (args.length == 1) {
                String reason = args[0];
                if (allowTp) {
                    allowTp = false;
                    Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + teleportMessage.getString("teleport-deny-reason").replaceAll("%reason%", reason)));
                } else {
                    allowTp = true;
                    Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + teleportMessage.getString("teleport-allow-reason").replaceAll("%reason%", reason)));
                }

                //携带过多参数，无法识别。
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + teleportMessage.getString("teleport-command-error")));
            }

            return true;
    }
}
