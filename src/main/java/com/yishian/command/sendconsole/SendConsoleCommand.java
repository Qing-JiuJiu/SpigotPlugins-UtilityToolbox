package com.yishian.command.sendconsole;

import com.yishian.common.CommonUtil;
import com.yishian.common.CommonMessageEnum;
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
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + SendConsoleConfigEnum.SENDCONSOLE_COMMAND_ERROR.getMsg()));
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

        String argString = stringBuilder.toString();

        //控制台执行指令
        Server server = Bukkit.getServer();
        boolean isSuccess = server.dispatchCommand(server.getConsoleSender(), argString);

        //判断是否执行成功
        if (isSuccess) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + SendConsoleConfigEnum.SENDCONSOLE_APPLY_SUCCESS.getMsg()).replaceAll("%command%", argString));
        }else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + SendConsoleConfigEnum.SENDCONSOLE_APPLY_FAIL.getMsg()).replaceAll("%command%", argString));
        }

        //打印警告日志
        CommonUtil.javaPlugin.getLogger().warning("玩家" + sender.getName() + "向控制台执行指令：" + argString);
        return true;
    }
}
