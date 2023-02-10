package com.yishian.command.sendconsole;

import com.yishian.common.CommonEnum;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * @author XinQi
 */
public class SendConsoleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //判断指令是否带参数
        if (args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + SendConsoleConfigEnum.SENDCONSOLE_COMMAND_ERROR.getMsg()));
            return true;
        }

        StringBuilder stringBuilder = new StringBuilder();
        //参数拼接
        for (int i = 0; i < args.length; i++) {
            stringBuilder.append(args[i]);
            if (i != args.length - 1) {
                stringBuilder.append(" ");
            }
        }

        //打包成字符串并发送消息给用户
        String argString = stringBuilder.toString();
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + SendConsoleConfigEnum.SENDCONSOLE_APPLY.getMsg().toString().replaceAll("%command%", argString)));

        //控制台执行指令
        Server server = Bukkit.getServer();
        server.dispatchCommand(server.getConsoleSender(), argString);

        return true;
    }
}
