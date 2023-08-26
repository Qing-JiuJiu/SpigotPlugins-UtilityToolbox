package com.yishian.command.killself;

import com.yishian.common.CommonMessageEnum;
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
public class KillSelfCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //判断执行指令的是用户还是控制台
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + CommonMessageEnum.CONSOLE_USE_OFFICIAL_COMMAND_TIPS.getMsg()));
            return true;
        }

        Player player = (Player) sender;
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + KillSelfConfigEnum.KILLSELF_APPLY.getMsg()));
        Server server = Bukkit.getServer();
        server.dispatchCommand(server.getConsoleSender(), "kill " + player.getName());
        return true;
    }
}
