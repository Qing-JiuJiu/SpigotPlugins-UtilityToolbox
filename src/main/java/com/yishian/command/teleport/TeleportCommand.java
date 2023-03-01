package com.yishian.command.teleport;

import com.yishian.common.PluginMessageConfigEnum;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * @author XinQi
 */
public class TeleportCommand implements CommandExecutor {

    /**
     * 默认允许传送，用于所有指令传送来判断是否允许传送
     */
    public static boolean allowTp = true;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //判断指令是否带参数
        if (args.length == 0) {
            if (allowTp) {
                allowTp = false;
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', PluginMessageConfigEnum.MESSAGE_PREFIX.getMsg() + TeleportConfigEnum.TELEPORT_DENY.getMsg()));
            } else {
                allowTp = true;
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', PluginMessageConfigEnum.MESSAGE_PREFIX.getMsg() + TeleportConfigEnum.TELEPORT_ALLOW.getMsg()));
            }

            return true;
        }

        //直接获取第一个参数当作原因
        String reason = args[0];
        if (allowTp) {
            allowTp = false;
            Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', PluginMessageConfigEnum.MESSAGE_PREFIX.getMsg() + TeleportConfigEnum.TELEPORT_DENY_REASON.getMsg()).replaceAll("%reason%", reason));
        } else {
            allowTp = true;
            Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', PluginMessageConfigEnum.MESSAGE_PREFIX.getMsg() + TeleportConfigEnum.TELEPORT_ALLOW_REASON.getMsg()).replaceAll("%reason%", reason));
        }

        return true;
    }
}
