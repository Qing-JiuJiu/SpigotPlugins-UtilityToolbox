package com.yishian.command.teleport;


import com.yishian.common.CommonEnum;
import com.yishian.common.CommonUtils;
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

    /**
     * 默认允许传送，用于所有指令传送来判断是否允许传送
     */
    public static boolean allowTp = true;

    /**
     * 获取配置文件里该指令的消息提示
     */
    static ConfigurationSection teleportMessage = CommonUtils.ServerConfig.getConfigurationSection(TeleportEnum.TELEPORT_COMMAND.getCommand()).getConfigurationSection(CommonEnum.MESSAGE.getCommand());

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //判断指令是否带参数
        if (args.length == 0) {
            if (allowTp) {
                allowTp = false;
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + teleportMessage.getString("teleport-deny")));
            } else {
                allowTp = true;
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + teleportMessage.getString("teleport-allow")));
            }

            //判断指令是否带一个参数，这个参数就是原因
        } else if (args.length == 1) {
            String reason = args[0];
            if (allowTp) {
                allowTp = false;
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + teleportMessage.getString("teleport-deny-reason").replaceAll("%reason%", reason)));
            } else {
                allowTp = true;
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + teleportMessage.getString("teleport-allow-reason").replaceAll("%reason%", reason)));
            }

            //携带过多参数，无法识别。
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + teleportMessage.getString("teleport-command-error")));
        }

        return true;
    }
}
