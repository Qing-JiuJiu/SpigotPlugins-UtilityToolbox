package com.yishian.command.kills;

import com.yishian.common.PluginMessageConfigEnum;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author XinQi
 */
public class KillSCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //判断指令是否带参数
        if (args.length != 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', PluginMessageConfigEnum.MESSAGE_PREFIX.getMsg() + KillSConfigEnum.KILLS_COMMAND_ERROR.getMsg()));
            return true;
        }

        //判断执行指令的是用户还是控制台
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', PluginMessageConfigEnum.MESSAGE_PREFIX.getMsg() + PluginMessageConfigEnum.CONSOLE_USE_OFFICIAL_COMMAND_TIPS.getMsg()));
            return true;
        }

        Player player = (Player) sender;
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', PluginMessageConfigEnum.MESSAGE_PREFIX.getMsg() + KillSConfigEnum.KILLS_APPLY.getMsg()));
        Server server = Bukkit.getServer();
        server.dispatchCommand(server.getConsoleSender(), "kill " + player.getName());
        return true;
    }
}
